package com.b2.backoffice.domain.like.service

import com.b2.backoffice.domain.exception.ModelNotFoundException
import com.b2.backoffice.domain.like.dto.LikeResponse
import com.b2.backoffice.domain.like.model.LikeEntity
import com.b2.backoffice.domain.like.repository.LikeRepository
import com.b2.backoffice.domain.post.repository.PostRepository
import com.b2.backoffice.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LikeServiceImpl(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository,
    private val likeRepository: LikeRepository,
) : LikeService {
    @Transactional
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
            post.increaseLikeCount()
            postRepository.save(post)
        } else throw Exception("Like is already exist")
    }

    @Transactional
    override fun deleteLike(userId: Int, postId: Int) {
        val post = postRepository.findByIdOrNull(postId)
            ?: throw ModelNotFoundException("post", postId)
        //포스트의 유저아이디와 좋아요를 누르는 유저아이디가 같은지 확인
        val like = likeRepository.findByUserIdAndPostId(userId, postId)
            ?: throw ModelNotFoundException("like", postId)
        likeRepository.delete(like)
        post.decreaseLikeCount()
        postRepository.save(post)
    }

    override fun getLike(postId: Int): LikeResponse? {
        val post = postRepository.findByIdOrNull(postId)
            ?: throw ModelNotFoundException("post", postId)
        return LikeResponse(
            postId = post.id!!,
            likeCount = likeRepository.findByPostId(post.id!!).size
        )
    }

}