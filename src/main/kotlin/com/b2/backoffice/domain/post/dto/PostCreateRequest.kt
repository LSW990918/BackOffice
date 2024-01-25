package com.b2.backoffice.domain.post.dto

import jakarta.validation.constraints.NotNull

data class PostCreateRequest(
    @field:NotNull(message = "Title must not be NULL")
    var title: String,
    @field:NotNull(message = "Contents must not be NULL")
    var contents: String,
)
