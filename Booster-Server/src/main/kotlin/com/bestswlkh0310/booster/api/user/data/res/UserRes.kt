package com.bestswlkh0310.booster.api.user.data.res

data class UserRes(
    val id: Long,
    val username: String,
    val nickname: String,
    val boostCount: Int
)