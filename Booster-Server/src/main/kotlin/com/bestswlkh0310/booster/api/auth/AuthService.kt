package com.bestswlkh0310.booster.api.auth

import com.bestswlkh0310.booster.internal.oauth2.google.GoogleOAuth2Helper
import com.bestswlkh0310.booster.api.auth.data.req.*
import com.bestswlkh0310.booster.api.auth.data.res.TokenRes
import com.bestswlkh0310.booster.internal.token.JwtClient
import com.bestswlkh0310.booster.api.auth.data.enumeration.JwtPayloadKey
import com.bestswlkh0310.booster.api.auth.data.enumeration.PlatformType
import com.bestswlkh0310.booster.foundation.user.UserRepository
import com.bestswlkh0310.booster.foundation.user.data.entity.User
import com.bestswlkh0310.booster.foundation.user.getByUsername
import com.bestswlkh0310.booster.api.core.data.res.BaseRes
import com.bestswlkh0310.booster.api.core.jpa.ReadOnlyTransactional
import com.bestswlkh0310.booster.global.exception.CustomException
import com.bestswlkh0310.booster.internal.oauth2.apple.AppleOAuth2Client
import com.bestswlkh0310.booster.internal.oauth2.apple.AppleOAuth2Helper
import com.bestswlkh0310.booster.internal.oauth2.google.GoogleOAuth2Client
import org.springframework.http.*
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@ReadOnlyTransactional
class AuthService(
    private val userRepository: UserRepository,
    private val encoder: BCryptPasswordEncoder,
    private val googleOAuth2Client: GoogleOAuth2Client,
    private val googleOAuth2Helper: GoogleOAuth2Helper,
    private val jwtUtils: JwtClient,
    private val appleOAuth2Client: AppleOAuth2Client,
    private val appleOAuth2Helper: AppleOAuth2Helper,
) {
    @Transactional(rollbackFor = [Exception::class])
    fun signUp(req: SignUpReq): BaseRes<TokenRes> {

        // validation
        if (req.password != req.passwordCheck) {
            throw CustomException(HttpStatus.BAD_REQUEST, "Password do not match")
        } else if (userRepository.existsByUsername(req.username)) {
            throw CustomException(HttpStatus.BAD_REQUEST, "User already exists")
        }

        // create user
        val user = userRepository.save(
            User(
                username = req.username,
                password = encoder.encode(req.password),
                nickname = req.nickname
            )
        )

        return BaseRes.ok(
            jwtUtils.generate(user)
        )
    }

    fun signIn(req: SignInReq): BaseRes<TokenRes> {
        // validation
        val user = userRepository.getByUsername(req.username)

        if (!encoder.matches(req.password, user.password)) {
            throw CustomException(HttpStatus.BAD_REQUEST, "Passwords do not match")
        }

        return BaseRes.ok(
            jwtUtils.generate(user)
        )
    }

    fun refresh(req: RefreshReq): BaseRes<TokenRes> {
        jwtUtils.parseToken(req.refreshToken)

        val user = run {
            val username = jwtUtils.payload(JwtPayloadKey.USERNAME, req.refreshToken)
            userRepository.getByUsername(username)
        }

        val token = jwtUtils.generate(user)
        return BaseRes.ok(
            data = TokenRes(
                accessToken = token.accessToken,
                refreshToken = req.refreshToken,
            )
        )
    }

    @Transactional(rollbackFor = [Exception::class])
    fun oAuth2SignIn(req: OAuth2SignInReq): BaseRes<TokenRes> {
        val token = when (req.platformType) {
            PlatformType.GOOGLE -> googleSignIn(req)
            PlatformType.APPLE -> appleSignIn(req)
            else -> throw CustomException(HttpStatus.BAD_REQUEST, "Invalid platform type")
        }
        return BaseRes.ok(
            jwtUtils.generate(token)
        )
    }

    private fun googleSignIn(req: OAuth2SignInReq): User {
        val token = googleOAuth2Client.getToken(code = req.code)
        val idToken = googleOAuth2Helper.verifyIdToken(token.idToken)
        val username = idToken.payload.email
        val users = userRepository.findByUsername(username)
        val user = users.firstOrNull() ?: userRepository.save(
            User(
                username = username,
                password = null,
                nickname = "", // TODO: Add nickname
                platformType = req.platformType
            )
        )
        return user
    }

    private fun appleSignIn(req: OAuth2SignInReq): User {
        val token = appleOAuth2Client.getToken(code = req.code)
        val headers = appleOAuth2Helper.parseHeader(idToken = token.idToken)
        val keys = appleOAuth2Client.getPublicKeys()
        val publicKey = appleOAuth2Helper.generate(headers = headers, keys = keys)
        val claims = appleOAuth2Helper.extractClaims(idToken = token.idToken, publicKey = publicKey)
        val username = claims["email"] as? String ?: throw CustomException(HttpStatus.BAD_REQUEST, "not found email")
        val users = userRepository.findByUsername(username)
        val user = users.firstOrNull() ?: userRepository.save(
            User(
                username = username,
                password = null,
                nickname = "", // TODO: Add nickname
                platformType = req.platformType
            )
        )
        return user
    }
}