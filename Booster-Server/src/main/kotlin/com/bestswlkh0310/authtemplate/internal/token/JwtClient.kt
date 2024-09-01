package com.bestswlkh0310.authtemplate.internal.token

import com.bestswlkh0310.authtemplate.api.auth.data.res.TokenRes
import com.bestswlkh0310.authtemplate.api.auth.data.enumeration.JwtPayloadKey
import com.bestswlkh0310.authtemplate.api.auth.data.enumeration.JwtSecretKeyType
import com.bestswlkh0310.authtemplate.foundation.user.data.entity.User
import com.bestswlkh0310.authtemplate.global.exception.CustomException
import com.bestswlkh0310.authtemplate.internal.core.OAuth2Properties
import io.jsonwebtoken.*
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.security.SignatureException
import java.util.*
import javax.crypto.spec.SecretKeySpec

@Component
class JwtClient(
    private val jwtProperties: JwtProperties,
    private val oAuth2Properties: OAuth2Properties,
) {
    fun payload(key: JwtPayloadKey, token: String, type: JwtSecretKeyType = JwtSecretKeyType.DEFAULT): String =
        parseToken(token, type).payload.get(key.key, String::class.java)

    fun parseToken(token: String, type: JwtSecretKeyType = JwtSecretKeyType.DEFAULT): Jws<Claims> =
        try {
            parser(type).parseSignedClaims(token)
        } catch (e: ExpiredJwtException) {
            throw CustomException(HttpStatus.FORBIDDEN, "token expired")
        } catch (e: SignatureException) {
            throw CustomException(HttpStatus.UNAUTHORIZED, "invalid token")
        } catch (e: MalformedJwtException) {
            throw CustomException(HttpStatus.UNAUTHORIZED, "invalid token")
        } catch (e: UnsupportedJwtException) {
            throw CustomException(HttpStatus.UNAUTHORIZED, "invalid token")
        } catch (e: IllegalArgumentException) {
            throw CustomException(HttpStatus.UNAUTHORIZED, "invalid token")
        } catch (e: Exception) {
            throw CustomException(HttpStatus.UNAUTHORIZED, "invalid token")
        }

    fun generate(user: User, type: JwtSecretKeyType = JwtSecretKeyType.DEFAULT) = TokenRes(
        accessToken = createToken(
            user = user,
            tokenExpired = jwtProperties.accessExpired,
            type = type
        ),
        refreshToken = createToken(
            user = user,
            tokenExpired = jwtProperties.refreshExpired,
            type = type
        )
    )

    private fun secretKey(type: JwtSecretKeyType) = SecretKeySpec(
        when (type) {
            JwtSecretKeyType.DEFAULT -> jwtProperties.secretKey
        }.toByteArray(StandardCharsets.UTF_8),
        Jwts.SIG.HS256.key().build().algorithm
    )

    private fun parser(type: JwtSecretKeyType) =
        Jwts.parser().verifyWith(secretKey(type)).build()

    private fun createToken(user: User, tokenExpired: Long, type: JwtSecretKeyType) =
        Jwts.builder()
            .claim(JwtPayloadKey.ID.key, user.id)
            .claim(JwtPayloadKey.USERNAME.key, user.username)
            .claim(JwtPayloadKey.ROLE.key, user.role)
            .issuedAt(Date())
            .expiration(Date(Date().time + tokenExpired))
            .signWith(secretKey(type))
            .compact()
}