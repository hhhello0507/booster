package com.bestswlkh0310.booster.foundation.boost.data.entity

import com.bestswlkh0310.booster.foundation.board.data.entity.Board
import com.bestswlkh0310.booster.foundation.user.data.entity.User
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "boost")
class Boost(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val count: Int,

    @ManyToOne(cascade = [CascadeType.REMOVE], fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_id", nullable = false)
    val user: User,

    @ManyToOne(cascade = [CascadeType.REMOVE], fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_board_id", nullable = false)
    val board: Board,
    
    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
)