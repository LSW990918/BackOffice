package com.b2.backoffice.domain.user.dto

import org.h2.bnf.context.DbContents

data class UserUpdateRequest(
    val password : String,
    val newPassword:String,
    val nickName : String,
)
