package com.b2.backoffice.domain.board.dto

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern

data class BoardUpdateRequest(
    @field:Pattern(
        regexp = "^[A-Za-z\\d!@#$%^&*?]{4,15}$",
        message = "Password must contain at least 4 characters, including at least one letter, one number, and one special character"
    )
    val password : String,

    @field:NotNull(message = "Title must not be NULL")
    var title : String,

    @field:NotNull(message = "Contents must not be NULL")
    var contents : String,
)
