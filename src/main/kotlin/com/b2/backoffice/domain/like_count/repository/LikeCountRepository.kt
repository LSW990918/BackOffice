package com.b2.backoffice.domain.like_count.repository


import com.b2.backoffice.domain.like.model.LikeEntity
import com.b2.backoffice.domain.like_count.model.LikeCountEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional

interface LikeCountRepository : JpaRepository<LikeEntity, Int> {
}