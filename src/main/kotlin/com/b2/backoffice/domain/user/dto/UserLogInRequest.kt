package com.b2.backoffice.domain.user.dto

data class UserLogInRequest(
    val email : String,
    val password : String,
)
