package com.bestswlkh0310.booster.api.board

import com.bestswlkh0310.booster.api.board.data.req.CreateBoardReq
import com.bestswlkh0310.booster.api.board.data.res.BoardRes
import com.bestswlkh0310.booster.api.core.data.res.BaseRes
import com.bestswlkh0310.booster.api.core.data.res.BaseVoidRes
import com.bestswlkh0310.booster.api.core.security.support.UserHolder
import com.bestswlkh0310.booster.foundation.board.BoardRepository
import com.bestswlkh0310.booster.foundation.board.data.entity.Board
import com.bestswlkh0310.booster.foundation.board.getBy
import com.bestswlkh0310.booster.foundation.user.UserRepository
import com.bestswlkh0310.booster.global.exception.CustomException
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class BoardService(
    private val boardRepository: BoardRepository,
    private val userRepository: UserRepository,
    private val userHolder: UserHolder
) {
    fun getAll(req: Pageable): BaseRes<List<BoardRes>> =
        BaseRes.ok(
            boardRepository.findWithPagination(req).toList()
                .map(BoardRes::of)
        )
    
    fun getMyBoards() = BaseRes.ok(
        userHolder.current().boards
    )

    fun createBoard(req: CreateBoardReq): BaseRes<Board> {
        val entity = Board(
            content = req.content,
            author = userHolder.current()
        )
        return BaseRes.created(
            boardRepository.save(entity)
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