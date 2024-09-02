package com.bestswlkh0310.booster.foundation.user.data.entity

import com.bestswlkh0310.booster.api.auth.data.enumeration.PlatformType
import com.bestswlkh0310.booster.foundation.board.data.entity.Board
import com.bestswlkh0310.booster.foundation.boost.data.entity.Boost
import com.bestswlkh0310.booster.foundation.user.data.enumeration.UserRole
import jakarta.persistence.*

@Entity(name = "`user`")
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

    @OneToMany(cascade = [(CascadeType.ALL)], orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "author")
    val boards: List<Board> = listOf(),

    @OneToMany(cascade = [(CascadeType.ALL)], orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "user")
    val boost: List<Boost> = listOf(),

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val platformType: PlatformType = PlatformType.DEFAULT
)