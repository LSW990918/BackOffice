//package com.b2.backoffice.domain.like_count.repository
//
//
//import com.b2.backoffice.domain.like.model.LikeEntity
//import com.b2.backoffice.domain.like_count.model.LikeCountEntity
//import org.springframework.data.jpa.repository.JpaRepository
//
//interface LikeCountRepository : JpaRepository<LikeCountEntity, Int> {
//    fun findByPostId(postId: Int): LikeCountEntity
//}