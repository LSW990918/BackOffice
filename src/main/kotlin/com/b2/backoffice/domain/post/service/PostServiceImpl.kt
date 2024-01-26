package com.b2.backoffice.domain.post.service

import com.b2.backoffice.domain.board.repository.BoardRepository
import com.b2.backoffice.domain.exception.ModelNotFoundException
import com.b2.backoffice.domain.post.dto.PostCreateRequest
import com.b2.backoffice.domain.post.dto.PostResponse
import com.b2.backoffice.domain.post.dto.PostUpdateRequest
import com.b2.backoffice.domain.post.model.PostEntity
import com.b2.backoffice.domain.post.repository.PostRepository
import com.b2.backoffice.domain.user.repository.UserRepository
import com.b2.backoffice.infra.security.SecurityService
import com.b2.backoffice.infra.security.UserPrincipal
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PostServiceImpl(
    private val postRepository: PostRepository,
    private val boardRepository: BoardRepository,
    private val userRepository: UserRepository,
    private val securityService: SecurityService,
) : PostService {
    override fun getPostList(
        boardId: Int,
    ): List<PostResponse> {
        return postRepository.findByBoardId(boardId).map {
            it.toResponse()
        }

    }

    override fun getAllPosts(): List<PostResponse> {
        return postRepository.findAll().map {
            it.toResponse()
        }

    }

    override fun getPost(
        boardId: Int,
        postId: Int
    ): PostResponse {
        val post = postRepository.findByIdOrNull(postId)
            ?: throw Exception()
        return post.toResponse()
    }

    override fun createPost(
        boardId: Int,
        userPrincipal: UserPrincipal,
        request: PostCreateRequest
    ): PostResponse {
        val board = boardRepository.findByIdOrNull(boardId)
            ?: throw ModelNotFoundException("board", boardId)
        val user = userRepository.findByIdOrNull(userPrincipal.id)
            ?: throw ModelNotFoundException("user", userPrincipal.id)
        val post = PostEntity(
            title = request.title,
            contents = request.contents,
            user = user,
            board = board,
            nickname = user.nickName,
        )
        board.createPost(post)
        postRepository.save(post)
        return post.toResponse()
    }

    override fun updatePost(
        boardId: Int,
        postId: Int,
        userPrincipal: UserPrincipal,
        request: PostUpdateRequest
    ): PostResponse {
        var board = boardRepository.findByIdOrNull(boardId)
            ?: throw ModelNotFoundException("board", boardId)
        val post = postRepository.findByIdOrNull(postId)
            ?: throw ModelNotFoundException("post", postId)
        val (title, contents) = request
        post.title = title
        post.contents = contents
        return post.toResponse()
    }

    override fun deletePost(
        boardId: Int,
        postId: Int,
        userPrincipal: UserPrincipal,
    ) {
        val board = boardRepository.findByIdOrNull(boardId)
            ?: throw ModelNotFoundException("board", boardId)
        val post = postRepository.findByIdOrNull(postId)
            ?: throw ModelNotFoundException("post", postId)
        securityService.chkUserId(userPrincipal, post.id!!)
        board.deletePost(post)
        postRepository.delete(post)
    }

}

fun PostEntity.toResponse(): PostResponse {
    return PostResponse(
        id = id!!,
        createAt = createdAt,
        nickname = nickname,
        title = title,
        contents = contents,
    )
}