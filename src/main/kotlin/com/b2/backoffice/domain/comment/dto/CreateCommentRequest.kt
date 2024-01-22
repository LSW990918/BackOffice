package com.b2.backoffice.domain.comment.dto

data class CreateCommentRequest(
    val content: String,
    val userid: Int,
)
