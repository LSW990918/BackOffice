package com.b2.backoffice.domain.comment.dto

data class CommentResponse(
    val content: String,
    val userid: Int?,
)