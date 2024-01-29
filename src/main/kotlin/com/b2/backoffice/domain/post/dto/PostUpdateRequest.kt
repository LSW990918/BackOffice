package com.b2.backoffice.domain.post.dto

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern

data class PostUpdateRequest(
    @field:NotNull(message = "Title must not be null")
    var title: String,
    @field:NotNull(message = "Contents must not be null")
    var contents: String,
)