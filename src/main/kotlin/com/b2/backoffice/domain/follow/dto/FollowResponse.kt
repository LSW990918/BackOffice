package com.b2.backoffice.domain.follow.dto

import com.b2.backoffice.domain.board.model.BoardEntity
import com.b2.backoffice.domain.user.model.UserEntity

data class FollowResponse (
    val userId: UserEntity,
    val boardId: BoardEntity,
)