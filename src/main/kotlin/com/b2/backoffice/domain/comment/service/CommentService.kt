package com.b2.backoffice.domain.comment.service

import com.b2.backoffice.domain.comment.dto.*

interface CommentService {
    fun createComment(postId: Int, userId: Int, request: CommentCreateRequest): CommentResponse

    fun deleteComment(userId : Int, postId : Int, commentId : Int )

    fun updateComment(postId: Int, commentId: Int, userId: Int, request: CommentUpdateRequest): CommentResponse

    fun getCommentListByPostId(postId: Int): List<CommentResponse>

}