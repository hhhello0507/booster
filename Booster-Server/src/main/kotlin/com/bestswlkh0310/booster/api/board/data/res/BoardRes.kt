package com.bestswlkh0310.booster.api.board.data.res

import com.bestswlkh0310.booster.api.user.data.res.UserRes
import com.bestswlkh0310.booster.foundation.board.data.entity.Board
import java.time.LocalDateTime

data class BoardRes(
    val id: Long,
    val content: String,
    val author: UserRes,
    val boostCount: Int,
    val createdAt: LocalDateTime
) {
    companion object {
        fun of(board: Board) = BoardRes(
            id = board.id,
            content = board.content,
            author = UserRes.of(board.author),
            boostCount = board.boosts.sumOf { it.count },
            createdAt = board.createdAt,
        )
    }
}