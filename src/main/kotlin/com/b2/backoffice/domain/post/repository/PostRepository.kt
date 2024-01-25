package com.b2.backoffice.domain.post.repository

import com.b2.backoffice.domain.post.model.PostEntity
import jakarta.persistence.Column
import org.springframework.data.jpa.repository.JpaRepository


interface PostRepository : JpaRepository<PostEntity, Int> {
    fun findByBoardId(boardId:Int): List<PostEntity>
}