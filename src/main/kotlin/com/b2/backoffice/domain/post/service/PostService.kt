package com.b2.backoffice.domain.post.service

import com.b2.backoffice.domain.post.dto.PostCreateRequest
import com.b2.backoffice.domain.post.dto.PostResponse
import com.b2.backoffice.domain.post.dto.PostUpdateRequest
import com.b2.backoffice.domain.post.dto.PostWithCommentResponse

interface PostService {
    fun getAllPosts(name: String? ): List<PostWithCommentResponse>
    fun getPost(boardId: Int, postId: Int) : PostResponse
    fun createPost(boardId: Int, request: PostCreateRequest) : PostResponse
    fun updatePost(boardId: Int, postId: Int, request: PostUpdateRequest) : PostResponse
    fun deletePost(boardId: Int, postId: Int)
    }

}