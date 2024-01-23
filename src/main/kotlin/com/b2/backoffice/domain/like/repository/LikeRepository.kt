package com.b2.backoffice.domain.like.repository


import com.b2.backoffice.domain.like.model.Like
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional

interface LikeRepository : JpaRepository<Like, Int> {
    fun findByUserIdAndPostId(userId: Int, postId: Int): Like?
    fun findByPostId(postId: Int): List<Like>

    @Transactional
    fun deleteByUserIdAndPostId(userId: Int, postId: Int)
}