package com.bestswlkh0310.booster.api.board

import com.bestswlkh0310.booster.api.board.data.req.CreateBoardReq
import com.bestswlkh0310.booster.api.board.data.res.BoardRes
import com.bestswlkh0310.booster.api.core.data.res.BaseRes
import com.bestswlkh0310.booster.api.core.data.res.BaseVoidRes
import com.bestswlkh0310.booster.api.core.security.support.UserHolder
import com.bestswlkh0310.booster.foundation.board.BoardRepository
import com.bestswlkh0310.booster.foundation.board.QueryDslBoardRepository
import com.bestswlkh0310.booster.foundation.board.data.entity.Board
import com.bestswlkh0310.booster.foundation.board.getBy
import com.bestswlkh0310.booster.foundation.user.UserRepository
import com.bestswlkh0310.booster.global.exception.CustomException
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BoardService(
    private val boardRepository: BoardRepository,
    private val queryDslBoardRepository: QueryDslBoardRepository,
    private val userRepository: UserRepository,
    private val userHolder: UserHolder
) {
    fun getAll(pageable: Pageable): BaseRes<List<BoardRes>> =
        BaseRes.ok(
            queryDslBoardRepository.getBoard(
                pageable = pageable,
                userId = userHolder.current().id
            )
        )
    
    fun getMyBoards() = BaseRes.ok(
        boardRepository.findByAuthorId(
            userHolder.current().id
        )
    )

    @Transactional
    fun createBoard(req: CreateBoardReq): BaseRes<BoardRes> {
        val entity = boardRepository.save(
            Board(
                content = req.content,
                author = userHolder.current()
            )
        )
        return BaseRes.created(
            BoardRes.of(
                board = entity,
                boosted = false
            )
        )
    }

    fun deleteBoard(boardId: Long): BaseVoidRes {
        val board = boardRepository.getBy(boardId)
        if (userHolder.current().id != board.author.id) {
            throw CustomException(HttpStatus.FORBIDDEN, "Invalid permission")
        }
        
        boardRepository.deleteById(boardId)
        return BaseVoidRes.ok()
    }
}