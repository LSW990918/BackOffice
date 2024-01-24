package com.b2.backoffice.domain.follow.model

import com.b2.backoffice.domain.board.model.BoardEntity
import com.b2.backoffice.domain.user.model.UserEntity
import jakarta.persistence.*

@Entity
@Table(name = "follow")
class FollowEntity(

    @ManyToOne
    @JoinColumn(name = "board_id")
    val board: BoardEntity,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: UserEntity

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null
}