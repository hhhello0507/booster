package com.bestswlkh0310.booster.api.user

import com.bestswlkh0310.booster.api.core.data.res.BaseRes
import com.bestswlkh0310.booster.api.core.security.support.UserHolder
import com.bestswlkh0310.booster.foundation.user.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val userHolder: UserHolder
) {
    fun getMe() = BaseRes.ok(
        userHolder.current()
    )
}