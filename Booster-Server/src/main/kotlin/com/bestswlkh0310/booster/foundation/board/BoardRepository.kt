package com.bestswlkh0310.booster.foundation.board

import com.bestswlkh0310.booster.foundation.board.data.entity.Board
import com.bestswlkh0310.booster.global.exception.CustomException
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus

interface BoardRepository : JpaRepository<Board, Long> {
    fun deleteByAuthorId(authorId: Long)
    fun findByAuthorId(authorId: Long): List<Board>
}

fun BoardRepository.getBy(id: Long) =
    findByIdOrNull(id) ?: throw CustomException(HttpStatus.NOT_FOUND, "Board not found")