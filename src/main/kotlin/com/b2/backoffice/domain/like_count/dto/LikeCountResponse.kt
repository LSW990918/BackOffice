package com.b2.backoffice.domain.like_count.dto

data class LikeCountResponse(
    val id: Int,
    val postId: Int,
    val likeCount: Int
)