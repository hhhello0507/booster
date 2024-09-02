package com.bestswlkh0310.booster.api.auth.infra

import com.bestswlkh0310.booster.foundation.user.UserRepository
import com.bestswlkh0310.booster.foundation.user.getByUsername
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class JwtUserDetailsService(
    private val userRepository: UserRepository,
) : UserDetailsService {
    @Transactional
    override fun loadUserByUsername(username: String) =
        JwtUserDetails(userRepository.getByUsername(username))
}