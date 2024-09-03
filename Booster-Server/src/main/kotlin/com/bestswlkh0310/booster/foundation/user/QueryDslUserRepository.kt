package com.bestswlkh0310.booster.foundation.user

import com.bestswlkh0310.booster.api.user.data.res.UserRes
import com.bestswlkh0310.booster.foundation.boost.data.entity.QBoost.boost
import com.bestswlkh0310.booster.foundation.user.data.entity.QUser.user
import com.bestswlkh0310.booster.global.exception.CustomException
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Repository

@Repository
class QueryDslUserRepository(
    private val factory: JPAQueryFactory
) {
    fun getUser(userId: Long): UserRes = factory.select(
        Projections.constructor(
            UserRes::class.java,
            user.id,
            user.username,
            user.nickname,
            user.booster
        )
    )
        .from(user)
        .where(user.id.eq(userId))
        .fetchFirst() ?: throw CustomException(HttpStatus.NOT_FOUND, "User not founded")
}