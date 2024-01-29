package com.b2.backoffice.domain.follow.repository

import com.b2.backoffice.domain.follow.model.FollowEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional

interface FollowRepository : JpaRepository<FollowEntity, Int> {
    fun findByUserIdAndBoardId(userId: Int, boardId: Int): FollowEntity?
    fun findAllByUserId(userId: Int): List<FollowEntity>
    //fun findByIdOrNull()
}