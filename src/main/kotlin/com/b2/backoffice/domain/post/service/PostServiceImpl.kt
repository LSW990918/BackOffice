package com.b2.backoffice.domain.post.service

import com.b2.backoffice.domain.post.dto.PostCreateRequest
import com.b2.backoffice.domain.post.dto.PostResponse
import com.b2.backoffice.domain.post.dto.PostUpdateRequest
import com.b2.backoffice.domain.post.model.PostEntity
import com.b2.backoffice.domain.post.repository.PostRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PostServiceImpl (
    private val postRepository: PostRepository
): PostService {
    override fun getPostList(boardId: Int): List<PostResponse> {
        TODO("Not yet implemented")
    }

    override fun getPost(boardId: Int, postId: Int): PostResponse {
        val post = postRepository.findByIdOrNull(postId)
            ?: throw Exception()
        return post.toResponse()
    }

    override fun createPost(boardId: Int, request: PostCreateRequest): PostResponse {
        TODO("Not yet implemented")
    }

    override fun updatePost(boardId: Int, postId: Int, request: PostUpdateRequest): PostResponse {
        TODO("Not yet implemented")
    }

    override fun deletePost(boardId: Int, postId: Int) {
        TODO("Not yet implemented")
    }

}

fun PostEntity.toResponse() : PostResponse {
    return PostResponse(
        id = id!!,
        createAt = createdAt,
        nickname = nickname,
        title = title,
        contents = contents,
        likes = likes,
    )
}