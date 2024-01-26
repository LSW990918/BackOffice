package com.b2.backoffice.domain.comment.service

import com.b2.backoffice.domain.comment.dto.*
import com.b2.backoffice.infra.security.UserPrincipal

interface CommentService {
    fun createComment(postId: Int, userPrincipal: UserPrincipal, request: CommentCreateRequest): CommentResponse

    fun deleteComment( postId : Int, commentId : Int, userPrincipal: UserPrincipal)

    fun updateComment(postId: Int, commentId: Int, userPrincipal: UserPrincipal, request: CommentUpdateRequest): CommentResponse

    fun getCommentByPostId(postId: Int): List<CommentResponse>

    fun getAllComments(): List<CommentResponse>
}