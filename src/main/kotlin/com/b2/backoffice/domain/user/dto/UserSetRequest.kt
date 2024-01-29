package com.b2.backoffice.domain.user.dto

import jakarta.validation.constraints.Pattern

data class UserSetRequest(
    // null 일 시 현상 유지
    val newPassword : String?,
    val nickName : String?,
    val introduce : String?,
    val role : String?
)
