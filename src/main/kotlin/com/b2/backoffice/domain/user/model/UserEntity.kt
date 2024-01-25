package com.b2.backoffice.domain.user.model

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.persistence.*
import org.hibernate.annotations.Where
import java.time.LocalDateTime

@Entity
@Table(name = "app_users")
@Where(clause = "is_deleted = false")
class UserEntity(
    @Column(name = "created_at")
    val createdAt : LocalDateTime = LocalDateTime.now(),

    @Column(name = "is_deleted")
    var isDeleted : Boolean = false,

    @Column(name = "email")
    val email : String,

    @Column(name = "password")
    var password : String,

    @ElementCollection
    @Column(name = "password_list")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    var passwordList: MutableList<String>,

    @Column(name = "nickname")
    var nickName : String,

    @Column(name = "role")
    var role : UserRole,
)
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Int? = null
}

