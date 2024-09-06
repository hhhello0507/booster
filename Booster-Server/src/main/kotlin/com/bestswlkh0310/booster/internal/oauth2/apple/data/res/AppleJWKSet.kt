package com.bestswlkh0310.booster.internal.oauth2.apple.data.res

import com.bestswlkh0310.booster.global.exception.CustomException
import org.springframework.http.HttpStatus

// Follow apple response name convention
// - Apple docs: https://developer.apple.com/documentation/sign_in_with_apple/jwkset
data class AppleJWKSet(
    val keys: List<Keys>
) {

    data class Keys(
        val kty: String,
        val kid: String,
        val use: String,
        val alg: String,
        val n: String,
        val e: String,
    )
    
    fun getMatchingKey(alg: String?, kid: String?) = keys
        .firstOrNull { key: Keys -> key.alg == alg && key.kid == kid }
        ?: throw CustomException(HttpStatus.BAD_REQUEST, "Invalid token")
}
