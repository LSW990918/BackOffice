package com.b2.backoffice.domain.user.repository

import com.b2.backoffice.domain.user.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Int>{
    fun existsByEmail(email : String) : Boolean

    fun findByEmail(email : String) : UserEntity
}