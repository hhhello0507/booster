package com.bestswlkh0310.booster.internal.oauth2.google.data.res

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class GoogleOAuth2TokenRes(
    @JsonProperty("access_token") val accessToken: String,
    @JsonProperty("expires_in") val expiresIn: Long,
    @JsonProperty("refresh_token") val refreshToken: String,
    @JsonProperty("scope") val scope: String,
    @JsonProperty("token_type") val tokenType: String,
    @JsonProperty("id_token") val idToken: String
)