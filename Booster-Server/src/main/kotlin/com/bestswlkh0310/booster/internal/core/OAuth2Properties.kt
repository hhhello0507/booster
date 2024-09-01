package com.bestswlkh0310.booster.internal.core

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class OAuth2Properties(
    @Value("\${oauth2.google.client-id.ios}") val googleClientIdIOS: String
)