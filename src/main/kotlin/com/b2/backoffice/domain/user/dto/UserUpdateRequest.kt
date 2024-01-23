package com.b2.backoffice.domain.user.dto

import org.h2.bnf.context.DbContents

data class UserUpdateRequest(
    val newPassword:String,
    val nickName : String,
)
