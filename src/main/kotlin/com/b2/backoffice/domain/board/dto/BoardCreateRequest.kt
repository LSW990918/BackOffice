package com.b2.backoffice.domain.board.dto

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class BoardCreateRequest(
    @field:Size(min=1, max= 100, message = "BoardTitle must be between 1 and 100 characters")
    var title : String,
    @field:Size(min=1, max= 100, message = "BoardContents must be between 1 and 100 characters")
    var contents : String,
)