package com.b2.backoffice.domain.follow.model

import com.b2.backoffice.domain.board.model.BoardEntity
import com.b2.backoffice.domain.user.model.UserEntity
import jakarta.persistence.*
import org.hibernate.annotations.Where

@Entity
@Table(name = "follow")
@Where(clause = "is_deleted = false")
class FollowEntity(

    @ManyToOne
    @JoinColumn(name = "board_id")
    val board: BoardEntity,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: UserEntity,

    @Column(name = "is_deleted")
    var isDeleted: Boolean = false

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null
}