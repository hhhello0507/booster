package com.bestswlkh0310.authtemplate.api.auth.data.req

import com.bestswlkh0310.authtemplate.api.auth.data.enumeration.PlatformType

data class OAuth2SignInReq(
    val platformType: PlatformType,
    val idToken: String
)