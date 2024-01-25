package com.b2.backoffice.domain.board.dto

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class BoardCreateRequest(
    @field:NotNull(message = "Title must not be NULL")
    var title : String,
    @field:NotNull(message = "Contents must not be NULL")
    var contents : String,
)