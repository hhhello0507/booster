package com.bestswlkh0310.booster.foundation.board

import com.bestswlkh0310.booster.foundation.board.data.entity.Board
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface BoardRepository : JpaRepository<Board, Long> {
    
    @Query("SELECT b FROM board b")
    fun findWithPagination(pageable: Pageable): List<Board>
    fun deleteByAuthorId(authorId: Long)
}