package com.bestswlkh0310.authtemplate.api.auth.data.req

import jakarta.validation.constraints.Size

data class SignInReq(
    @Size(min = 2, max = 24)
    val username: String,
    
    @Size(min = 2, max = 24)
    val password: String
)