package com.b2.backoffice.domain.comment.dto

import jakarta.validation.constraints.Pattern

data class CommentUpdateRequest(
    @field:Pattern(
        regexp = "^[A-Za-z\\d!@#$%^&*?]{1,100}$",
        message = " 댓글은 1-100자 사이로 입력 바랍니다. "
    )
    val content: String
)