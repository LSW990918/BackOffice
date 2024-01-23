package com.b2.backoffice.domain.post.dto

data class PostUpdateRequest(
    var title: String,
    var contents: String,
)