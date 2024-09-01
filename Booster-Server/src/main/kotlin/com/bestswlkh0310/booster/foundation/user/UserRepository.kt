package com.bestswlkh0310.booster.foundation.user

import com.bestswlkh0310.booster.foundation.user.data.entity.User
import com.bestswlkh0310.booster.global.exception.CustomException
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun existsByUsername(username: String): Boolean
    fun findByUsername(username: String): List<User>
}

fun UserRepository.getByUsername(username: String): User =
    findByUsername(username).firstOrNull() ?: throw CustomException(HttpStatus.NOT_FOUND, "User not found")