package com.b2.backoffice.domain.user.dto

import jakarta.validation.constraints.Pattern

data class UserUpdateRequest(
    @field:Pattern(
        regexp = "^[A-Za-z\\d!@#$%^&*?]{4,15}$",
        message = "Password must contain at least 4 characters, including at least one letter, one number, and one special character"
    )
    val password : String,

    @field:Pattern(
        regexp = "^[A-Za-z\\d!@#$%^&*?]{4,15}$",
        message = "Password must contain at least 4 characters, including at least one letter, one number, and one special character"
    )
    val newPassword:String,

    @field:Pattern(
        regexp = "^[A-Za-z\\d]{4,10}$",
        message = "NickName must contain at least 4 characters, including letter, number, and no special character"
    )
    val nickName : String,
)
