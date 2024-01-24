package com.b2.backoffice.domain.like.repository


import com.b2.backoffice.domain.like.model.LikeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional

interface LikeRepository : JpaRepository<LikeEntity, Int> {
    fun findByUserIdAndPostId(userId: Int, postId: Int): LikeEntity?
    fun findByPostId(postId: Int): List<LikeEntity>

    @Transactional
    fun deleteByUserIdAndPostId(userId: Int, postId: Int)
}