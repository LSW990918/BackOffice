package com.b2.backoffice.domain.follow.service

import com.b2.backoffice.domain.board.dto.BoardResponse
import com.b2.backoffice.domain.board.model.toResponse
import com.b2.backoffice.domain.board.repository.BoardRepository
import com.b2.backoffice.domain.follow.model.FollowEntity
import com.b2.backoffice.domain.follow.repository.FollowRepository
import com.b2.backoffice.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull

class FollowServiceImpl(
    private val followRepository: FollowRepository,
    private val userRepository: UserRepository,
    private val boardRepository: BoardRepository
):FollowService {
    override fun addFollow(userId: Int, boardId: Int) {
        val board = boardRepository.findByIdOrNull(boardId)
            ?: throw Exception()
        val user = userRepository.findByIdOrNull(userId)
            ?: throw Exception()
        if (followRepository.findByUserIdAndBoardId(userId, boardId) == null) {
            followRepository.save(
                FollowEntity(
                    user = user,
                    board = board
                )
            )
        } else throw Exception()
    }

    override fun cancleFollow(userId: Int, boardId: Int) {
        val follow = followRepository.findByUserIdAndBoardId(userId, boardId)
            ?: throw Exception()
        followRepository.delete(follow)
    }

    override fun getFollowList(userId: Int): List<BoardResponse> {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw Exception()
        val followList = followRepository.findAllByUserId(user.id!!).map { it.board.toResponse() }
        return followList
    }
}