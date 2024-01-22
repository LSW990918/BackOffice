package com.b2.backoffice.user.dto

data class UserSignUpRequest (
    val email: String,
    val password: String,
    val nickname : String,
    val role : String,
)