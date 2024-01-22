package com.b2.backoffice.domain.comment.service


import com.b2.backoffice.domain.comment.dto.CommentResponse
import com.b2.backoffice.domain.comment.dto.CreateCommentRequest
import com.b2.backoffice.domain.comment.dto.UpdateCommentRequest
import com.b2.backoffice.domain.comment.model.CommentEntity
import com.b2.backoffice.domain.comment.repository.CommentRepository
import com.b2.backoffice.domain.exception.ModelNotFoundException
import com.b2.backoffice.domain.post.repository.PostRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentServiceImpl(

private val postRepository: PostRepository,
private val commentRepository: CommentRepository

): CommentService {

@Transactional
    override fun createComment(
    postId: Int,
    userId: Int,
    request: CreateCommentRequest
    ): CommentResponse {
        val post = postRepository.findByIdOrNull (postId.toLong())
            ?: throw ModelNotFoundException("post", postId)

        val comment = CommentEntity(
            content = request.content,
            post = post
        )

        post.createComment(comment)
        postRepository.save(post)
        return comment.toResponse()
    }
    @Transactional
    override fun deleteComment(
        userId: Int,
        postId : Int,
        commentId : Int,
        ) {
        val post = postRepository.findByIdOrNull(postId)
            ?: throw ModelNotFoundException("post", postId)
        val comment = commentRepository.findByIdOrNull(commentId)
            ?: throw ModelNotFoundException("Comment", commentId)

        post.deleteComment(comment)
        postRepository.save(post)
    }
@Transactional
    override fun updateComment(
    postId: Int,
    commentId: Int,
    userId: Int,
    request: UpdateCommentRequest
    ): CommentResponse {
        val post = postRepository.findByIdOrNull(postId)
            ?: throw ModelNotFoundException("post", postId)
        val comment = commentRepository.findByIdOrNull(commentId)
            ?: throw ModelNotFoundException("Comment", commentId)

        comment.content = request.content
        return comment.toResponse()
    }
@Transactional
    override fun getCommentByPostId(postId: Int): List<CommentResponse> {
    val post = postRepository.findByIdOrNull(postId)
        ?: throw ModelNotFoundException("Post", postId)
    return post.comments.map { it.toResponse() }
    }
}