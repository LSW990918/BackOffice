package com.b2.backoffice.domain.follow.service

import com.b2.backoffice.domain.board.dto.BoardResponse
import com.b2.backoffice.domain.board.repository.BoardRepository
import com.b2.backoffice.domain.board.service.toResponse
import com.b2.backoffice.domain.exception.ModelNotFoundException
import com.b2.backoffice.domain.follow.model.FollowEntity
import com.b2.backoffice.domain.follow.repository.FollowRepository
import com.b2.backoffice.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class FollowServiceImpl(
    private val followRepository: FollowRepository,
    private val userRepository: UserRepository,
    private val boardRepository: BoardRepository
) : FollowService {
    override fun follow(userId: Int, boardId: Int) {
        val board = boardRepository.findByIdOrNull(boardId)
            ?: throw ModelNotFoundException("board", boardId)
        val user = userRepository.findByIdOrNull(userId)
            ?: throw ModelNotFoundException("user", userId)
        if (followRepository.findByUserIdAndBoardId(userId, boardId) == null) {
            followRepository.save(
                FollowEntity(
                    user = user,
                    board = board
                )
            )
        } else throw Exception("follow is already added")
    }

    override fun unfollow(userId: Int, boardId: Int) {
        val follow = followRepository.findByUserIdAndBoardId(userId, boardId)
            ?: throw ModelNotFoundException("follow", boardId)
        follow.isDeleted = true
    }

    override fun getFollowList( userId: Int): List<BoardResponse> {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw ModelNotFoundException("User", userId)
        val followList = followRepository.findAllByUserId(user.id!!).map { it.board.toResponse() }
        return followList
    }
}