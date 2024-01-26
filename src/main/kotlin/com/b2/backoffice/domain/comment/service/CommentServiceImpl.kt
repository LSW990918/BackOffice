package com.b2.backoffice.domain.comment.service

import com.b2.backoffice.domain.comment.dto.CommentCreateRequest
import com.b2.backoffice.domain.comment.dto.CommentResponse
import com.b2.backoffice.domain.comment.dto.CommentUpdateRequest
import com.b2.backoffice.domain.comment.model.CommentEntity
import com.b2.backoffice.domain.comment.repository.CommentRepository
import com.b2.backoffice.domain.exception.ModelNotFoundException
import com.b2.backoffice.domain.post.repository.PostRepository
import com.b2.backoffice.domain.user.repository.UserRepository
import com.b2.backoffice.infra.security.SecurityService
import com.b2.backoffice.infra.security.UserPrincipal
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentServiceImpl(
    private val postRepository: PostRepository,
    private val commentRepository: CommentRepository,
    private val userRepository: UserRepository,
    private val securityService: SecurityService
) : CommentService {

    @Transactional
    override fun createComment(
        postId: Int,
        userPrincipal: UserPrincipal,
        request: CommentCreateRequest
    ): CommentResponse {
        val post = postRepository.findByIdOrNull(postId)
            ?: throw ModelNotFoundException("post", postId)
        val user = userRepository.findByIdOrNull(userPrincipal.id)
            ?: throw ModelNotFoundException("user", userPrincipal.id)

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
        postId: Int,
        commentId: Int,
        userPrincipal: UserPrincipal
    ) {
        val post = postRepository.findByIdOrNull(postId)
            ?: throw ModelNotFoundException("post", postId)
        val comment = commentRepository.findByIdOrNull(commentId)
            ?: throw ModelNotFoundException("Comment", commentId)
        val user = userRepository.findByIdOrNull(userPrincipal.id)
            ?: throw ModelNotFoundException("user", userPrincipal.id)

        securityService.chkUserId(userPrincipal, user.id!!)
        commentRepository.delete(comment)
        postRepository.save(post)
    }

    @Transactional
    override fun updateComment(
        postId: Int,
        commentId: Int,
        userPrincipal: UserPrincipal,
        request: CommentUpdateRequest
    ): CommentResponse {
        val post = postRepository.findByIdOrNull(postId)
            ?: throw ModelNotFoundException("post", postId)
        val comment = commentRepository.findByIdOrNull(commentId)
            ?: throw ModelNotFoundException("Comment", commentId)
        val user = userRepository.findByIdOrNull(userPrincipal.id)
            ?: throw ModelNotFoundException("user", userPrincipal.id)

        securityService.chkUserId(userPrincipal, user.id!!)
        comment.content = request.content
        val updatedComment = commentRepository.save(comment)
        postRepository.save(post)

        return updatedComment.toResponse()
    }

    @Transactional
    override fun getCommentListByPostId(
        postId: Int
    ): List<CommentResponse> {
        val post = postRepository.findByIdOrNull(postId)
            ?: throw ModelNotFoundException("Post", postId)
        return commentRepository.findByPostId(post.id!!).map { it.toResponse() }
    }
}

fun CommentEntity.toResponse(): CommentResponse {
    return CommentResponse(
        id = id,
        content = content,
        nickname = user.nickName,
        createdAt = createdAt
    )
}

