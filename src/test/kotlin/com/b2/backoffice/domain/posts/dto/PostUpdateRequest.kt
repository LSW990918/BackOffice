package com.b2.backoffice.domain.posts.dto

data class PostUpdateRequest(
    val a : String
    val password : String,
    var nickname : String,
    var title : String,
    var contents : String,
    var likes : Int,
)