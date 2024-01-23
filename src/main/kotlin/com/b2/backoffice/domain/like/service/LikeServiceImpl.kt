package com.b2.backoffice.domain.like.service

import com.b2.backoffice.domain.like.dto.LikeResponse
import com.b2.backoffice.domain.like.repository.LikeRepository
import org.springframework.stereotype.Service

@Service
class LikeServiceImpl(
    //private val postRepository: PostRepository,
    //private val userRepository: UserRepository,
    private val likeRepository: LikeRepository,
) : LikeService {
    override fun createLike(userId: Int, postId: Int) {
        TODO()
    }

    override fun deleteLike(userId: Int, postId: Int) {
        TODO()
    }

    override fun getLike(postId: Int): LikeResponse? {
        TODO()
    }

}