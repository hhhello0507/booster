package com.bestswlkh0310.booster.api.core.security.support

import com.bestswlkh0310.booster.api.auth.infra.JwtUserDetails
import com.bestswlkh0310.booster.foundation.user.data.entity.User
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional


@Component
class UserHolder {
    fun current() =
        (SecurityContextHolder.getContext().authentication.principal as JwtUserDetails).user
}
