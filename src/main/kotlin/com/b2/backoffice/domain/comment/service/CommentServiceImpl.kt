package com.b2.backoffice.domain.comment.service

import com.b2.backoffice.domain.comment.dto.CommentResponse
import com.b2.backoffice.domain.comment.dto.CommentCreateRequest
import com.b2.backoffice.domain.comment.dto.CommentUpdateRequest
import com.b2.backoffice.domain.comment.model.CommentEntity
import com.b2.backoffice.domain.comment.repository.CommentRepository
import com.b2.backoffice.domain.exception.ModelNotFoundException
import com.b2.backoffice.domain.post.repository.PostRepository
import com.b2.backoffice.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentServiceImpl(
    private val postRepository: PostRepository,
    private val commentRepository: CommentRepository,
    private val userRepository: UserRepository
) : CommentService {

    @Transactional
    override fun createComment(
        postId: Int,
        userId: Int,
        request: CommentCreateRequest
    ): CommentResponse {
        val post = postRepository.findByIdOrNull(postId)
            ?: throw ModelNotFoundException("post", postId)
        val user = userRepository.findByIdOrNull(userId)
            ?: throw ModelNotFoundException("user", userId)

        val comment = CommentEntity(
            content = request.content,
            post = post,
            user = user,
            nickName = user.nickName,
        )

        val savedComment = commentRepository.save(comment)

        return savedComment.toResponse()
    }

    @Transactional
    override fun deleteComment(
        userId: Int,
        postId: Int,
        commentId: Int
    ) {
        val post = postRepository.findByIdOrNull(postId)
            ?: throw ModelNotFoundException("post", postId)
        val comment = commentRepository.findByIdOrNull(commentId)
            ?: throw ModelNotFoundException("Comment", commentId)

        /* post.deleteComment(comment)*/
        commentRepository.delete(comment)
        postRepository.save(post)
    }

    @Transactional
    override fun updateComment(
        postId: Int,
        commentId: Int,
        userId: Int,
        request: CommentUpdateRequest
    ): CommentResponse {
        val post = postRepository.findByIdOrNull(postId)
            ?: throw ModelNotFoundException("post", postId)
        val comment = commentRepository.findByIdOrNull(commentId)
            ?: throw ModelNotFoundException("Comment", commentId)

        comment.content = request.content
        val updatedComment = commentRepository.save(comment)

        return updatedComment.toResponse()
    }

    @Transactional
    override fun getCommentByPostId(
        postId: Int
    ): List<CommentResponse> {
        val post = postRepository.findByIdOrNull(postId)
            ?: throw ModelNotFoundException("Post", postId)
        val commentList = commentRepository.findByPostId(post.id!!).map { it.toResponse() }
        return commentList
    }

    override fun getAllComments(): List<CommentResponse> {
        val commentList = commentRepository.findAll().map { it.toResponse() }
        return commentList
    }
}

fun CommentEntity.toResponse(): CommentResponse {
    return CommentResponse(
        id = id,
        content = content,
        nickname = user.nickName,
        createdAt = createAt
    )
}
