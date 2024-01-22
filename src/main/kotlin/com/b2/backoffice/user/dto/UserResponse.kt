package com.b2.backoffice.user.dto

import java.time.LocalDateTime

data class UserResponse(
    val id:Int,
    val createAt: LocalDateTime,
    val nickName : String,
    val email : String,
    val role : String,

)