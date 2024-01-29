package com.b2.backoffice.domain.user.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class UserLogInRequest(

    @field:Size(min=4, max= 20, message = "Email must be between 4 and 20 characters")
    val email : String,

    @field:Pattern(
        regexp = "^[A-Za-z\\d!@#$%^&*?]{4,15}$",
        message = "Password must contain at least 4 characters, including at least one letter, one number, and one special character"
    )
    val password : String,
)
