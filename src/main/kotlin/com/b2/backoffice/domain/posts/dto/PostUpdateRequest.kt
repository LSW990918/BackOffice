package com.b2.backoffice.domain.posts.dto

data class PostUpdateRequest(
    var title: String,
    var contents: String,
)