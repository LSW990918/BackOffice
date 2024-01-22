package com.b2.backoffice.domain.post.service

import com.b2.backoffice.domain.post.dto.CreatePostRequest
import com.b2.backoffice.domain.post.dto.PostResponse
import com.b2.backoffice.domain.post.dto.PostWithCommentResponse
import com.b2.backoffice.domain.post.dto.UpdatePostRequest

interface PostService {
    fun getAllPosts(name: String? ): List<PostWithCommentResponse>
    fun getPost(postId: Int): PostWithCommentResponse
    fun createPost(request: CreatePostRequest): PostResponse
    fun updatePost(postId: Int, request: UpdatePostRequest): PostResponse
    fun deletePost(postId: Int)

}
