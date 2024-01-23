package com.b2.backoffice.domain.like.dto

data class LikeResponse(
    val Id: Int,
    val postId: Int,
    val userId: Int,
)