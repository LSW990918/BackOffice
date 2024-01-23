package com.b2.backoffice.domain.board.dto

import java.time.LocalDateTime

data class BoardResponse(
    val id : Int, // 자동 생성
    var createAt : LocalDateTime,
    var title : String,
    var contents : String,
)