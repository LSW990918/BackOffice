package com.b2.backoffice.domain.posts.dto

import java.time.LocalDateTime


data class PostResponse(
    val id: Int, // 자동 생성
    var createAt: LocalDateTime,
    var nickname: String,
    var title: String,
    var contents: String,
    var likes: Int,
)