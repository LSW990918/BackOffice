package com.b2.backoffice.domain.post.model


import com.b2.backoffice.domain.comment.model.CommentEntity
import com.b2.backoffice.domain.post.dto.PostResponse
import com.b2.backoffice.domain.post.dto.PostWithCommentResponse
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
//@Table(name = "Posts")

class PostEntity(
    @Column(name = "created_at")
    val createdAt : LocalDateTime = LocalDateTime.now(),

    @Column(name = "nickname")
    var nickname : String,

    @Column(name = "title")
    var title : String,

    @Column(name = "contents")
    var contents : String,

    @Column(name = "likes")
    var likes : Int,


)

{
    @Id
    @GeneratedValue(strategy = GenerationType. IDENTITY)
    val id : Int? = null
}


fun PostEntity.toResponse() : PostResponse {
    return PostResponse(
    id = id!!,
    createAt = createdAt,
    nickname = nickname,
    title = title,
    contents = contents,
    likes = likes,
    )
}


