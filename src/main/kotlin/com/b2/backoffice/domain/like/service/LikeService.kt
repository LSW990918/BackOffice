package com.b2.backoffice.domain.like.service

import com.b2.backoffice.domain.like_count.dto.LikeCountResponse

interface LikeService {
    //인증/인가 완료시 userId 받아오기
    fun createLike(userId: Int, postId: Int)
    fun deleteLike(userId: Int, postId: Int)
    fun getLike(postId: Int): LikeCountResponse?

}