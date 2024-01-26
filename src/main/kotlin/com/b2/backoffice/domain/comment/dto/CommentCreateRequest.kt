package com.b2.backoffice.domain.comment.dto

import jakarta.validation.constraints.Size

data class CommentCreateRequest(
    @field:Size(min=1, max= 500, message = " 댓글은 1-250자 사이로 입력 바랍니다. ")
    val content: String,
)


