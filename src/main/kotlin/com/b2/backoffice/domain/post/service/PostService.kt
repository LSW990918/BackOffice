package com.b2.backoffice.domain.post.service

import com.b2.backoffice.domain.post.dto.PostCreateRequest
import com.b2.backoffice.domain.post.dto.PostResponse
import com.b2.backoffice.domain.post.dto.PostUpdateRequest
import com.b2.backoffice.domain.post.dto.PostWithCommentResponse
import com.b2.backoffice.infra.security.UserPrincipal

interface PostService {
    fun getPostList(boardId: Int): List<PostResponse>
    fun getAllPosts(): List<PostResponse>
    fun getPost(boardId: Int, postId: Int): PostResponse
    fun createPost(
        boardId: Int,
        userPrincipal: UserPrincipal,
        request: PostCreateRequest
    ): PostResponse

    fun updatePost(
        boardId: Int,
        postId: Int,
        userPrincipal: UserPrincipal,
        request: PostUpdateRequest
    ): PostResponse

    fun deletePost(
        boardId: Int,
        postId: Int,
        userPrincipal: UserPrincipal
    )
}