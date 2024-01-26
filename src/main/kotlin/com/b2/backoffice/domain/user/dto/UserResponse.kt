package com.b2.backoffice.domain.user.dto

import org.springframework.cglib.core.Local
import java.time.LocalDateTime

data class UserResponse(
    val id:Int,
    val createAt: LocalDateTime,
    val modifiedAt : LocalDateTime?,
    val email : String,
    val nickName : String,
    val introduce : String,
    val role : String,

)
