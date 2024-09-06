package com.bestswlkh0310.booster.internal.oauth2.apple

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class AppleOAuth2Properties(
    @Value("\${oauth2.apple.grant-type}") val grantType: String,
    @Value("\${oauth2.apple.bundle-id}") val bundleId: String,
    @Value("\${oauth2.apple.key-id}") val keyId: String,
    @Value("\${oauth2.apple.team-id}") val teamId: String,
    @Value("\${oauth2.apple.audience}") val audience: String,
    @Value("\${oauth2.apple.private-key}") val privateKey: String,
)