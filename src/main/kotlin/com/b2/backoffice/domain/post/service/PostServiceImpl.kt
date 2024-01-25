package com.b2.backoffice.domain.post.service

import com.b2.backoffice.domain.exception.ModelNotFoundException
import com.b2.backoffice.domain.post.dto.PostCreateRequest
import com.b2.backoffice.domain.post.dto.PostResponse
import com.b2.backoffice.domain.post.dto.PostUpdateRequest
import com.b2.backoffice.domain.post.dto.PostWithCommentResponse
import com.b2.backoffice.domain.post.model.PostEntity
import com.b2.backoffice.domain.post.repository.PostRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PostServiceImpl(
    private val postRepository: PostRepository
) : PostService {
    override fun getPostList(
        boardId: Int,
    ): List<PostResponse> {
        return postRepository.findByBoardId(boardId = boardId).map {
            it.toResponse()
        }

    }

    override fun getAllPosts(): List<PostResponse> {
        return postRepository.findAll().map {
            it.toResponse()
        }

    }

    override fun getPost(
        boardId: Int,
        postId: Int
    ): PostResponse {
        val post = postRepository.findByIdOrNull(postId)
            ?: throw Exception()
        return post.toResponse()
    }

    override fun createPost(
        boardId: Int,
        userId: Int,
        request: PostCreateRequest
    ): PostResponse {
    }

    override fun updatePost(
        boardId: Int,
        postId: Int,
        userId: Int,
        request: PostUpdateRequest
    ): PostResponse {
        val post = postRepository.findByIdOrNull(postId)
            ?: throw ModelNotFoundException("Post", postId)
    }

    override fun deletePost(
        boardId: Int,
        postId: Int,
        userId: Int,
    ): PostResponse {
        val post = postRepository.findByIdOrNull(postId)
            ?
    }

}

fun PostEntity.toResponse(): PostResponse {
    return PostResponse(
        id = id!!,
        createAt = createdAt,
        nickname = nickname,
        title = title,
        contents = contents,
        likes = likes,
    )
}