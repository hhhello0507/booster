package com.bestswlkh0310.booster.internal.oauth2.apple.data.res

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

// Follow apple response name convention
// - Apple docs: https://developer.apple.com/documentation/sign_in_with_apple/tokenresponse
@JsonIgnoreProperties(ignoreUnknown = true)
data class AppleTokenRes(
    @JsonProperty("access_token") val accessToken: String,
    @JsonProperty("token_type") val tokenType: String,
    @JsonProperty("expires_in") val expiresIn: Long,
    @JsonProperty("refresh_token") val refreshToken: String,
    @JsonProperty("id_token") val idToken: String,
)