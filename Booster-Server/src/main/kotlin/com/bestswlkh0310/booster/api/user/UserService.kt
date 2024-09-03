package com.bestswlkh0310.booster.api.user

import com.bestswlkh0310.booster.api.core.data.res.BaseRes
import com.bestswlkh0310.booster.api.core.jpa.ReadOnlyTransactional
import com.bestswlkh0310.booster.api.core.security.support.UserHolder
import com.bestswlkh0310.booster.api.user.data.res.UserRes
import com.bestswlkh0310.booster.foundation.boost.BoostRepository
import com.bestswlkh0310.booster.foundation.user.QueryDslUserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@ReadOnlyTransactional
class UserService(
    private val boostRepository: BoostRepository,
    private val userHolder: UserHolder,
    private val queryDslUserRepository: QueryDslUserRepository
) {
    fun getMe(): BaseRes<UserRes> {
        val user = userHolder.current()
        return BaseRes.ok(
            queryDslUserRepository.getUser(userId =  user.id)
        )
    }
}