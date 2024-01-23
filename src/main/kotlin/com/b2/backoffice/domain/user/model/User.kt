package com.b2.backoffice.domain.user.model

import com.b2.backoffice.domain.user.dto.UserResponse
import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.persistence.*
import java.time.LocalDateTime
import java.time.LocalTime

@Entity
@Table(name = "users")
class User(
    @Column(name = "created_at")
    val createdAt : LocalDateTime = LocalDateTime.now(),

    @Column(name = "email")
    val email : String,

    @Column(name = "password")
    val password : String,

    @ElementCollection
    @Column(name = "password_list")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    var passwordList: List<String>? = null,

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

fun User.toResponse() : UserResponse {
    return UserResponse(
        id = id!!,
        createAt = createdAt,
        email = email,
        nickName = nickName,
        role = role.name
    )
}