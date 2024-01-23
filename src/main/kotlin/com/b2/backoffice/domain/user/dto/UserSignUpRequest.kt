package com.b2.backoffice.domain.user.dto

data class UserSignUpRequest (
    val email: String,
    val password: String,
    val nickname : String,
    val role : String,
)