package com.b2.backoffice.domain.post.model

import com.b2.backoffice.domain.post.dto.PostResponse
import com.b2.backoffice.domain.post.dto.PostWithCommentResponse


class PostEntity {
}
fun PostEntity.toResponse(): PostResponse {
    return PostResponse(
        id = id!!,
        status = status,
        title = title,
        content = content,
        createdAt = createdAt,
        name = name
    )

}
fun PostEntity.toListResponse(): PostWithCommentResponse {
    return PostWithCommentResponse(
        id = id!!,
        status = status,
        title = title,
        content = content,
        createdAt = createdAt,
        name = name,
        comments = comments.map { it.toResponse() }
    )
}