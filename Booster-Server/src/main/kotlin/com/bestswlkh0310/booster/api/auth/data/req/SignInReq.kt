package com.bestswlkh0310.booster.api.auth.data.req

import jakarta.validation.constraints.Size

data class SignInReq(
    @field:Size(min = 2, max = 24)
    val username: String,
    
    @field:Size(min = 2, max = 24)
    val password: String
)