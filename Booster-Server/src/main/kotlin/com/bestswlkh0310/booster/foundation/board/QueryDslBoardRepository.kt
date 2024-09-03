package com.bestswlkh0310.booster.foundation.board

import com.bestswlkh0310.booster.api.board.data.res.BoardRes
import com.bestswlkh0310.booster.api.user.data.res.UserRes
import com.bestswlkh0310.booster.foundation.board.data.entity.QBoard.board
import com.bestswlkh0310.booster.foundation.boost.data.entity.QBoost.boost
import com.querydsl.core.types.Projections
import com.querydsl.core.types.dsl.Expressions
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class QueryDslBoardRepository(
    private val factory: JPAQueryFactory
) {
    fun getBoard(pageable: Pageable, userId: Long): List<BoardRes> {
        return factory
            .select(
                Projections.constructor(
                    BoardRes::class.java,
                    board.id,
                    board.content,
                    Projections.constructor(
                        UserRes::class.java,
                        board.author.id,
                        board.author.username,
                        board.author.nickname,
                        board.author.boostCount
                    ),
                    board.boostCount,
                    Expressions.asBoolean(boost.isNotNull),
                    board.createdAt
                )
            )
            .from(board)
            .leftJoin(boost).on(board.id.eq(boost.board.id).and(board.author.id.eq(boost.user.id)))
            .orderBy(board.createdAt.desc())
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()
    }
}