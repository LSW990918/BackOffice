package com.b2.backoffice.domain.comment.repository

import com.b2.backoffice.domain.comment.model.CommentEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository: JpaRepository <CommentEntity, Int> {

}