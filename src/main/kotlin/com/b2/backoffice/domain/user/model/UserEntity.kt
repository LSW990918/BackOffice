package com.b2.backoffice.domain.user.model

import com.b2.backoffice.domain.user.dto.UserResponse
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "users")
class UserEntity(
    @Column(name = "created_at")
    val createdAt : LocalDateTime = LocalDateTime.now(),

    @Column(name = "email")
    val email : String,

    @Column(name = "password")
    val password : String,

    @Column(name = "nickName")
    var nickName : String,

    @Column(name = "role")
    var role : UserRole,
)
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Int? = null
}

fun UserEntity.toResponse() : UserResponse {
    return UserResponse(
        id = id!!,
        createAt = createdAt,
        email = email,
        nickName = nickName,
        role = role.name
    )
}