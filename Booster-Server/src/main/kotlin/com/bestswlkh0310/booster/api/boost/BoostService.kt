package com.bestswlkh0310.booster.api.boost

import com.bestswlkh0310.booster.api.board.data.res.BoardRes
import com.bestswlkh0310.booster.api.boost.data.req.CreateBoostReq
import com.bestswlkh0310.booster.api.core.data.res.BaseRes
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
    fun createBoost(req: CreateBoostReq): BaseRes<BoardRes> {
        val user = userHolder.current()
        val board = boardRepository.getBy(req.boardId)
        val exists = boostRepository.existsByUserIdAndBoardId(
            boardId = req.boardId,
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

        board.boostCount += 1
        user.booster += addBoostCount

        userRepository.save(user)
        boardRepository.save(board)

        return BaseRes.ok(
            BoardRes.of(
                board = board,
                boosted = true
            )
        )
    }
}