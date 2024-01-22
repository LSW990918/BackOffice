package com.b2.backoffice.domain.board.dto

data class BoardCreateRequest(
    var title : String,
    var contents : String,
)