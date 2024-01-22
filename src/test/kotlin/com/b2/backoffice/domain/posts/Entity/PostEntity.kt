package com.b2.backoffice.domain.posts.Entity

import com.b2.backoffice.domain.posts.dto.PostResponse
import com.b2.backoffice.domain.posts.dto.createAt
import java.time.LocalDateTime

@Entity
//@Table(name = "Posts")

class Post(
    @Column(name = "created_at")
    val createdAt : LocalDateTime = LocalDateTime.now()

    @Column(name = "email")
    val emial : String,

    @Column(name = "password")
    var password : String,

    @Column(name = "nickname")
    var nickname : String,

    @Column(name = "title")
    var title : String,

    @Column(name = "contents")
    var contents : String,

    @Column(name = "likes")
    var likes : Int
)
{
    @Id
    @GeneratedValue(strategy = GenerationType. IDENTITY)
    val id : Int? = null
}

fun Post.toResponse() : PostResponse{
    return PostResponse(
    id = id!!,
    createAt = createdAt
    email = email,
    nickname = nickname,
    title = title,
    contents = contents
    likes = likes
    )
}