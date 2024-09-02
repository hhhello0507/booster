package com.bestswlkh0310.booster.api.board.data.res

import com.bestswlkh0310.booster.api.user.data.res.UserRes
import java.time.LocalDateTime

data class BoardRes(
    val id: Long,
    val content: String,
    val author: UserRes,
    val boostCount: Int,
    val createdAt: LocalDateTime
)