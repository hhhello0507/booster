package com.bestswlkh0310.booster.api.auth.data.req

import jakarta.validation.constraints.Size

data class SignUpReq(
    @field:Size(min = 2, max = 24)
    val username: String,

    @field:Size(min = 2, max = 24)
    val password: String,

    @field:Size(min = 2, max = 24)
    val passwordCheck: String,

    @field:Size(min = 2, max = 24)
    val nickname: String
) {
}