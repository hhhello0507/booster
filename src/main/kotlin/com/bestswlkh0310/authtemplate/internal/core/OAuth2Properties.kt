package com.bestswlkh0310.authtemplate.internal.core

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class OAuth2Properties(
    @Value("\${oauth2.google.client-id.ios}") val googleClientIdIOS: String,
    @Value("\${oauth2.google.client-id.web}") val googleClientIdWeb: String,
    @Value("\${oauth2.google.client-secret}") val googleClientSecret: String,
)