package com.bestswlkh0310.booster.internal.oauth2.google

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class GoogleOAuth2Properties(
    @Value("\${oauth2.google.client-id.ios}") val iOSClientId: String,
    @Value("\${oauth2.google.client-id.web}") val webClientId: String,
    @Value("\${oauth2.google.client-secret}") val clientSecret: String,
    @Value("\${oauth2.google.redirect-uri}") val redirectUri: String,
    @Value("\${oauth2.google.token-uri}") val tokenUri: String,
    @Value("\${oauth2.google.resource-uri}") val resourceUri: String,
    @Value("\${oauth2.google.grant-type}") val grantType: String,
)