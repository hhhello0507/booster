package com.bestswlkh0310.booster.foundation.board.data.entity

import com.bestswlkh0310.booster.foundation.user.data.entity.User
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "board")
class Board(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val content: String,

    @ManyToOne(cascade = [CascadeType.REMOVE], fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_id", nullable = false)
    val author: User,

    @Column(nullable = false)
    var boostCount: Int = 0,

    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),
)