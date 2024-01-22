package com.b2.backoffice.domain.post.dto

import com.b2.backoffice.domain.comment.dto.CommentResponse

data class PostWithCommentResponse(
    val id: Int,
    val title: String,
    val content: String?,
    val createdAt: String,
    val name: String,
    val comments: List<CommentResponse>
)
