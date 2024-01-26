package com.b2.backoffice.domain.like.service

import com.b2.backoffice.domain.exception.ModelNotFoundException
import com.b2.backoffice.domain.like.model.LikeEntity
import com.b2.backoffice.domain.like.repository.LikeRepository
import com.b2.backoffice.domain.like_count.dto.LikeCountResponse
import com.b2.backoffice.domain.like_count.repository.LikeCountRepository
import com.b2.backoffice.domain.post.repository.PostRepository
import com.b2.backoffice.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class LikeServiceImpl(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository,
    private val likeRepository: LikeRepository,
    private val likeCountRepository: LikeCountRepository
) : LikeService {
    override fun createLike(userId: Int, postId: Int) {
        val post = postRepository.findByIdOrNull(postId)
            ?: throw ModelNotFoundException("post", postId)
        val user = userRepository.findByIdOrNull(userId)
            ?: throw ModelNotFoundException("user", userId)
        //포스트의 유저아이디와 좋아요를 누르는 유저아이디가 같은지 확인
        if (post.user.id == userId) {
            throw Exception()
        }
        if (likeRepository.findByUserIdAndPostId(userId, postId) == null) {
            likeRepository.save(
                LikeEntity(
                    post,
                    user
                )
            )
            val likeCount = likeCountRepository.findByPostId(postId)
            likeCount.increaseLikeCount()
        } else throw Exception("Like is already exist")
    }

    override fun deleteLike(userId: Int, postId: Int) {
        //포스트의 유저아이디와 좋아요를 누르는 유저아이디가 같은지 확인
        val like = likeRepository.findByUserIdAndPostId(userId, postId)
            ?: throw ModelNotFoundException("like", postId)
        like.isDeleted = true
        val likeCount = likeCountRepository.findByPostId(postId)
        likeCount.decreaseLikeCount()

    }

    override fun getLike(postId: Int): LikeCountResponse? {
        val post = postRepository.findByIdOrNull(postId)
            ?: throw ModelNotFoundException("post", postId)
        val likeCount = likeCountRepository.findByPostId(post.id!!)
        return LikeCountResponse(
            postId = likeCount.post.id!!,
            likeCount = likeCount.likeCount)
    }

}