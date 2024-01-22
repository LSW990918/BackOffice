package com.b2.backoffice.domain.comment.model

import com.b2.backoffice.domain.comment.dto.CommentResponse
import com.b2.backoffice.domain.post.model.PostEntity
import jakarta.persistence.*

@Entity
class CommentEntity (

    @Column(name = "name")
    var name: String,

    @Column(name = "content")
    var content: String,

    @ManyToOne
    @JoinColumn(name = "card_id")
    val post: PostEntity

    )
fun CommentEntity.toResponse(): CommentResponse {
    return CommentResponse(
        id = id!!,
        name = name,
        content = content,
        createdAt = createdAt
    )
}