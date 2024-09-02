package com.bestswlkh0310.booster.foundation.board.data.entity

import com.bestswlkh0310.booster.foundation.boost.data.entity.Boost
import com.bestswlkh0310.booster.foundation.user.data.entity.User
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "board")
class Board(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_id", nullable = false)
    val author: User,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "board")
    val boosts: List<Boost> = listOf(),

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    val createdAt: LocalDateTime = LocalDateTime.now(),
)