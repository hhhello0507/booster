package com.bestswlkh0310.authtemplate.api.auth.data.req

import jakarta.validation.constraints.Size

data class SignUpReq(
    @Size(min = 2, max = 24)
    val username: String,

    @Size(min = 2, max = 24)
    val password: String,

    @Size(min = 2, max = 24)
    val passwordCheck: String,

    @Size(min = 2, max = 24)
    val nickname: String
) {
}