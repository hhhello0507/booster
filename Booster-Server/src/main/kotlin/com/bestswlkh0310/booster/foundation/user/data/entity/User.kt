package com.bestswlkh0310.booster.foundation.user.data.entity

import com.bestswlkh0310.booster.api.auth.data.enumeration.PlatformType
import com.bestswlkh0310.booster.foundation.user.data.enumeration.UserRole
import jakarta.persistence.*

@Entity(name = "user")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    val id: Long = 0,

    @Column(nullable = false)
    val username: String,

    val password: String?,

    @Column(nullable = false)
    val nickname: String,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val role: UserRole = UserRole.USER,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val platformType: PlatformType = PlatformType.DEFAULT,

    @Column(nullable = false)
    var booster: Int = 0,
)