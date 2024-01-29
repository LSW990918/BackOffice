package com.b2.backoffice.domain.like.dto

data class LikeResponse(
    val postId: Int,
    val likeCount: Int,
)