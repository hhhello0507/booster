package com.bestswlkh0310.booster.api.auth.data.req

import com.bestswlkh0310.booster.api.auth.data.enumeration.PlatformType

data class OAuth2SignInReq(
    val platformType: PlatformType,
    val code: String,
)