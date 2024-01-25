package com.b2.backoffice.domain.board.model

import com.b2.backoffice.domain.board.dto.BoardResponse
import com.b2.backoffice.domain.user.dto.UserResponse
import com.b2.backoffice.domain.user.model.UserEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "boards")
class BoardEntity(
    @Column(name = "created_at")
    val createdAt : LocalDateTime = LocalDateTime.now(),

    @Column(name = "is_deleted")
    var isDeleted : Boolean = false,

    @Column(name = "title")
    var title : String,

    @Column(name = "contents")
    var contents : String,
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Int? = null
}

