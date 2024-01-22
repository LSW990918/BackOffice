package com.b2.backoffice.domain.comment.service

import com.b2.backoffice.domain.comment.dto.*

interface CommentService {
    fun createComment(postId: Int, userId: Int, request: CreateCommentRequest): CommentResponse

    fun deleteComment(userId : Int, postId : Int, commentId : Int )

    fun updateComment(postId: Int, commentId: Int, userId: Int, request: UpdateCommentRequest): CommentResponse

    fun getCommentByPostId(postId: Int): List<CommentResponse>
}