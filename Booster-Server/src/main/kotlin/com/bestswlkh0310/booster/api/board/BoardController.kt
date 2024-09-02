package com.bestswlkh0310.booster.api.board

import com.bestswlkh0310.booster.api.board.data.req.CreateBoardReq
import jakarta.validation.Valid
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/board")
class BoardController(
    private val boardService: BoardService
) {
    @GetMapping
    fun getAllBoard(
        @PageableDefault(
            size = 15,
            page = 0,
            sort = ["createdAt"]
        ) req: Pageable
    ) = boardService.getAll(req = req)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createBoard(@RequestBody @Valid req: CreateBoardReq) =
        boardService.createBoard(req = req)

    @DeleteMapping("/{boardId}")
    fun deleteBoard(@PathVariable boardId: Long) =
        boardService.deleteBoard(boardId = boardId)
}