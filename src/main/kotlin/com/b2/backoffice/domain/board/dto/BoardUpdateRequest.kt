package com.b2.backoffice.domain.board.dto

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class BoardUpdateRequest(
    @field:Pattern(
        regexp = "^[A-Za-z\\d!@#$%^&*?]{4,15}$",
        message = "Password must contain at least 4 characters, including at least one letter, one number, and one special character"
    )
    val password : String,

    @field:Size(min=1, max= 100, message = "BoardTitle must be between 1 and 100 characters")
    var title : String,

    @field:Size(min=1, max= 100, message = "BoardContents must be between 1 and 100 characters")
    var contents : String,
)
