package com.b2.backoffice.domain.comment.dto

import com.b2.backoffice.domain.comment.model.CommentEntity
import java.time.LocalDateTime

data class CommentResponse (
    val id: Int?,
    val nickname: String,
    val content: String,
    val createdAt: LocalDateTime?
)

fun CommentEntity.toResponse(): CommentResponse {
    return CommentResponse(
        id = id,
        content = content,
        nickname = user.nickName,
        createdAt = createAt
    )
}
