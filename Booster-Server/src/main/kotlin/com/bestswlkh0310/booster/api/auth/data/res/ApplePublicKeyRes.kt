package com.bestswlkh0310.booster.api.auth.data.res

data class ApplePublicKeyRes(
    val kty: String,
    val kid: String,
    val use: String,
    val alg: String,
    val n: String,
    val e: String,
)