package com.b2.backoffice.user.dto

import org.h2.bnf.context.DbContents

data class UserUpdateRequest(
    val password:String,
    val nickName : String,
)
