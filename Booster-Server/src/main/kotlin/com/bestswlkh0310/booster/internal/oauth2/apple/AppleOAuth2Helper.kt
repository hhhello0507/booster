package com.bestswlkh0310.booster.internal.oauth2.apple

import com.bestswlkh0310.booster.global.exception.CustomException
import com.bestswlkh0310.booster.internal.oauth2.apple.data.res.AppleJWKSet
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.*
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import java.math.BigInteger
import java.security.*
import java.security.spec.InvalidKeySpecException
import java.security.spec.RSAPublicKeySpec
import java.util.*

@Component
class AppleOAuth2Helper(
    private val objectMapper: ObjectMapper,
) {

    companion object {
        private const val IDENTITY_TOKEN_VALUE_DELIMITER: String = "\\."
        private const val HEADER_INDEX: Int = 0

        private const val SIGN_ALGORITHM_HEADER: String = "alg"
        private const val KEY_ID_HEADER: String = "kid"
        private const val POSITIVE_SIGN_NUMBER: Int = 1
    }

    fun parseHeader(idToken: String): Map<String, String> {
        try {
            val encodedHeader: String = idToken.split(IDENTITY_TOKEN_VALUE_DELIMITER.toRegex())
                .dropLastWhile { it.isEmpty() }
                .toTypedArray()[HEADER_INDEX]
            val decodedHeader = String(Base64.getUrlDecoder().decode(encodedHeader))
            return objectMapper.readValue(decodedHeader, object : TypeReference<Map<String, String>>() {})
        } catch (e: JsonMappingException) {
            throw RuntimeException("appleToken 값이 jwt 형식인지, 값이 정상적인지 확인해주세요.")
        } catch (e: JsonProcessingException) {
            throw RuntimeException("디코드된 헤더를 Map 형태로 분류할 수 없습니다. 헤더를 확인해주세요.")
        }
    }
    
    fun generate(headers: Map<String, String>, keys: AppleJWKSet): PublicKey {
        val applePublicKey = keys.getMatchingKey(
            headers[SIGN_ALGORITHM_HEADER],
            headers[KEY_ID_HEADER]
        )
        return generatePublicKey(applePublicKey)
    }

    private fun generatePublicKey(applePublicKey: AppleJWKSet.Keys): PublicKey {
        val nBytes: ByteArray = Base64.getUrlDecoder().decode(applePublicKey.n)
        val eBytes: ByteArray = Base64.getUrlDecoder().decode(applePublicKey.e)

        val n = BigInteger(POSITIVE_SIGN_NUMBER, nBytes)
        val e = BigInteger(POSITIVE_SIGN_NUMBER, eBytes)
        val rsaPublicKeySpec = RSAPublicKeySpec(n, e)

        return try {
            val keyFactory = KeyFactory.getInstance(applePublicKey.kty)
            keyFactory.generatePublic(rsaPublicKeySpec)
        } catch (exception: NoSuchAlgorithmException) {
            throw java.lang.RuntimeException("잘못된 애플 키")
        } catch (exception: InvalidKeySpecException) {
            throw java.lang.RuntimeException("잘못된 애플 키")
        }
    }

    fun extractClaims(idToken: String, publicKey: PublicKey): Claims = try {
        Jwts.parser()
            .verifyWith(publicKey)
            .build()
            .parseSignedClaims(idToken)
            .payload
    } catch (e: UnsupportedJwtException) {
        throw UnsupportedJwtException("지원되지 않는 jwt 타입")
    } catch (e: IllegalArgumentException) {
        throw IllegalArgumentException("비어있는 jwt")
    } catch (e: JwtException) {
        throw JwtException("jwt 검증 or 분석 오류")
    }
}