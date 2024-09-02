package com.bestswlkh0310.booster.api.boost

import com.bestswlkh0310.booster.api.core.data.res.BaseVoidRes
import com.bestswlkh0310.booster.api.core.security.support.UserHolder
import com.bestswlkh0310.booster.foundation.board.BoardRepository
import com.bestswlkh0310.booster.foundation.board.data.entity.Board
import com.bestswlkh0310.booster.foundation.board.getBy
import com.bestswlkh0310.booster.foundation.boost.BoostRepository
import com.bestswlkh0310.booster.foundation.boost.data.entity.Boost
import org.springframework.stereotype.Service
import kotlin.random.Random
import kotlin.random.nextInt

@Service
class BoostService(
    private val boostRepository: BoostRepository,
    private val userHolder: UserHolder,
    private val boardRepository: BoardRepository
) {
    fun postBoost(boardId: Long): BaseVoidRes {
        val user = userHolder.current()
        val board = boardRepository.getBy(boardId)
        val boost = boostRepository.findByUserIdAndBoardId(
            boardId = boardId,
            userId = user.id
        )
        
        if (boost == null) {
            boostRepository.save(
                Boost(
                    count = Random.nextInt(10..<20),
                    user = user,
                    board = board
                )
            )
        } else {
            boardRepository.deleteById(boardId)
        }
        return BaseVoidRes.ok()
    }
}