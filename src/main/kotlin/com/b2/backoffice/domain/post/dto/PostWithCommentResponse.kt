package com.b2.backoffice.domain.post.dto

import com.b2.backoffice.domain.comment.dto.CommentResponse

data class PostWithCommentResponse(
    val id: Int,
    val title: String,
    val contents: String?,
    val createdAt: String,
    val nickname: String,
    val comments: List<CommentResponse>

)
