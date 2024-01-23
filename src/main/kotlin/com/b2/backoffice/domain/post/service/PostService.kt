package com.b2.backoffice.domain.post.service

import com.b2.backoffice.domain.post.dto.PostCreateRequest
import com.b2.backoffice.domain.post.dto.PostResponse
import com.b2.backoffice.domain.post.dto.PostUpdateRequest
import com.b2.backoffice.domain.post.dto.PostWithCommentResponse

interface PostService {
    interface PostService {
        fun getAllPosts(name: String? ): List<PostWithCommentResponse>
        fun getPost(postId: Int): PostWithCommentResponse
        fun createPost(request: PostCreateRequest): PostResponse
        fun updatePost(postId: Int, request: PostUpdateRequest): PostResponse
        fun deletePost(postId: Int)

    }
}