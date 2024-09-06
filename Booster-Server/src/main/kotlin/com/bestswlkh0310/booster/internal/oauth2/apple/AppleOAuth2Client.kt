package com.bestswlkh0310.booster.internal.oauth2.apple

import com.bestswlkh0310.booster.global.exception.CustomException
import com.bestswlkh0310.booster.internal.oauth2.apple.data.res.AppleJWKSet
import com.bestswlkh0310.booster.internal.oauth2.apple.data.res.AppleTokenRes
import io.jsonwebtoken.Jwts
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo
import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import org.springframework.web.client.toEntity
import java.security.PrivateKey
import java.security.Security
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*


@Component
class AppleOAuth2Client(
    @Qualifier("apple")
    private val restClient: RestClient,
    private val properties: AppleOAuth2Properties,
) {

    fun getToken(code: String) = restClient.post()
        .uri {
            it.path("/auth/token")
                .queryParam("client_id", properties.bundleId)
                .queryParam("client_secret", generateClientSecret())
                .queryParam("grant_type", properties.grantType)
                .queryParam("code", code)
                .build()
        }
        .retrieve()
        .toEntity<AppleTokenRes>()
        .body ?: throw CustomException(HttpStatus.BAD_REQUEST, "Apple client error")

    fun getPublicKeys() = restClient.get()
        .uri {
            it.path("/auth/keys")
                .build()
        }
        .retrieve()
        .toEntity(AppleJWKSet::class.java)
        .body ?: throw CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "Apple client error")


    private fun generateClientSecret(): String {
        val expiration = LocalDateTime.now().plusMinutes(5)

        return Jwts.builder()
            .header()
            .keyId(properties.keyId)
            .and()
            .issuer(properties.teamId)
            .audience()
            .add(properties.audience)
            .and()
            .subject(properties.bundleId)
            .issuedAt(Date())
            .expiration(Date.from(expiration.atZone(ZoneId.systemDefault()).toInstant()))
            .signWith(getPrivateKey(), Jwts.SIG.ES256)
            .compact()
    }

    private fun getPrivateKey(): PrivateKey {
        Security.addProvider(BouncyCastleProvider())
        val converter = JcaPEMKeyConverter().setProvider("BC")

        try {
            val privateKeyBytes = Base64.getDecoder().decode(properties.privateKey)

            val privateKeyInfo = PrivateKeyInfo.getInstance(privateKeyBytes)
            return converter.getPrivateKey(privateKeyInfo)
        } catch (e: Exception) {
            throw java.lang.RuntimeException("Error converting private key from String", e)
        }
    }
}