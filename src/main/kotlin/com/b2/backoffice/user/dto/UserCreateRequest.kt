package com.b2.backoffice.user.dto

data class UserCreateRequest (
    val email: String,
    val password: String,
    val nickname : String,
)