package com.bestswlkh0310.booster.foundation.boost

import com.bestswlkh0310.booster.foundation.boost.data.entity.Boost
import org.springframework.data.jpa.repository.JpaRepository

interface BoostRepository: JpaRepository<Boost, Long> {
    fun findByUserIdAndBoardId(userId: Long, boardId: Long): Boost?
}