package com.b2.backoffice.domain.board.dto

data class BoardUpdateRequest(
    val password : String,
    var title : String,
    var contents : String,
)
