package com.bestswlkh0310.booster.api.boost

import com.bestswlkh0310.booster.api.core.data.res.BaseVoidRes
import com.bestswlkh0310.booster.api.core.security.support.UserHolder
import com.bestswlkh0310.booster.foundation.board.BoardRepository
import com.bestswlkh0310.booster.foundation.board.getBy
import com.bestswlkh0310.booster.foundation.boost.BoostRepository
import com.bestswlkh0310.booster.foundation.boost.data.entity.Boost
import com.bestswlkh0310.booster.foundation.user.UserRepository
import com.bestswlkh0310.booster.global.exception.CustomException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.random.Random
import kotlin.random.nextInt

@Service
class BoostService(
    private val boostRepository: BoostRepository,
    private val userHolder: UserHolder,
    private val boardRepository: BoardRepository,
    private val userRepository: UserRepository
) {
    @Transactional
    fun createBoost(boardId: Long): BaseVoidRes {
        val user = userHolder.current()
        val board = boardRepository.getBy(boardId)
        val exists = boostRepository.existsByUserIdAndBoardId(
            boardId = boardId,
            userId = user.id
        )

        if (exists) {
            throw CustomException(HttpStatus.BAD_REQUEST, "User already create boost")
        }
        
        val addBoostCount = Random.nextInt(10..<20)
        boostRepository.save(
            Boost(
                count = addBoostCount,
                user = user,
                board = board
            )
        )
        
        board.boostCount += addBoostCount
        user.boostCount += addBoostCount
        
        userRepository.save(user)
        boardRepository.save(board)
        
        return BaseVoidRes.ok()
    }
}