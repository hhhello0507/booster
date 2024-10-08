package com.bestswlkh0310.booster.api.user.data.res

import com.bestswlkh0310.booster.foundation.user.data.entity.User

data class UserRes(
    val id: Long,
    val username: String,
    val nickname: String,
    val booster: Int
) {
    companion object {
        fun of(user: User) = UserRes(
            id = user.id,
            username = user.username,
            nickname = user.nickname,
            booster = user.booster
        )
    }
}