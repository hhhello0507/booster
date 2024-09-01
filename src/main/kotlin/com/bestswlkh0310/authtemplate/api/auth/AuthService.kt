package com.bestswlkh0310.authtemplate.api.auth

import com.bestswlkh0310.authtemplate.api.auth.data.req.*
import com.bestswlkh0310.authtemplate.api.auth.data.res.TokenRes
import com.bestswlkh0310.authtemplate.internal.token.JwtClient
import com.bestswlkh0310.authtemplate.api.auth.data.enumeration.JwtPayloadKey
import com.bestswlkh0310.authtemplate.foundation.user.UserRepository
import com.bestswlkh0310.authtemplate.foundation.user.data.entity.User
import com.bestswlkh0310.authtemplate.foundation.user.getByUsername
import com.bestswlkh0310.authtemplate.api.core.data.BaseRes
import com.bestswlkh0310.authtemplate.global.exception.CustomException
import com.bestswlkh0310.authtemplate.internal.oauth2.GoogleOAuth2Client
import org.springframework.http.*
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service


@Service
class AuthService(
    private val userRepository: UserRepository,
    private val encoder: BCryptPasswordEncoder,
    private val googleOAuth2Client: GoogleOAuth2Client,
    private val jwtUtils: JwtClient
) {
    fun signUp(req: SignUpReq): BaseRes<TokenRes> {

        // validation
        if (req.password != req.passwordCheck) {
            throw CustomException(HttpStatus.BAD_REQUEST, "Password do not match")
        } else if (userRepository.existsByUsername(req.username)) {
            throw CustomException(HttpStatus.BAD_REQUEST, "Already exists user")
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

        return BaseRes.ok(
            jwtUtils.generate(user)
        )
    }

    fun oAuth2SignIn(req: OAuth2SignInReq): BaseRes<TokenRes> {
        // validation
        val idToken = googleOAuth2Client.verifyIdToken(req.idToken)
        val (username, name) = idToken.payload.let { listOf(it.email, it.subject) }
        val users = userRepository.findByUsername(username)
        val user = users.firstOrNull() ?: userRepository.save(
            User(
                username = username,
                password = null,
                nickname = name,
                platformType = req.platformType
            )
        )

        return BaseRes.ok(
            jwtUtils.generate(user)
        )
    }
}