package com.b2.backoffice.domain.post.dto

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class PostCreateRequest(
    @field:Size(min = 1,max = 100, message = "Title should be within 100 characters")
    var title: String,
    @field:Size(min = 1, max = 1000, message = "Contents should be within 1000 characters")
    var contents: String,
)