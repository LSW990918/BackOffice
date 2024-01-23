package com.b2.backoffice.user.repository

import com.b2.backoffice.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>{
    fun existsByEmail(email : String) : Boolean

    fun findByEmail(email : String) : User
}