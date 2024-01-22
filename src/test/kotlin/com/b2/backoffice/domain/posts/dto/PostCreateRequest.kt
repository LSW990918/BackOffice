package com.b2.backoffice.domain.posts.dto

data class PostCreateRequest (
    val a:String
    var nickname: String,
    var title: String,
    var contents: String,
    var likes: Int,
)
