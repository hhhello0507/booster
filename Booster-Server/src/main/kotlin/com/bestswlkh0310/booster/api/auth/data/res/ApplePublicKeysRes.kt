package com.bestswlkh0310.booster.api.auth.data.res

import com.bestswlkh0310.booster.global.exception.CustomException
import org.springframework.http.HttpStatus

data class ApplePublicKeysRes(
    val keys: List<ApplePublicKeyRes>
) {

    fun getMatchingKey(alg: String?, kid: String?) = keys
        .firstOrNull { key: ApplePublicKeyRes -> key.alg == alg && key.kid == kid }
        ?: throw CustomException(HttpStatus.BAD_REQUEST, "Invalid token")
}