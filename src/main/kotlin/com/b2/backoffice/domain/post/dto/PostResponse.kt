package com.b2.backoffice.domain.post.dto

import com.b2.backoffice.domain.post.model.PostEntity
import java.time.LocalDateTime


data class PostResponse(
    val id: Int, // 자동 생성
    var createAt: LocalDateTime,
    var nickname: String,
    var title: String,
    var contents: String,
    var likeCount: Int
)
