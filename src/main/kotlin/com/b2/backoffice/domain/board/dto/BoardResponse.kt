package com.b2.backoffice.domain.board.dto

import com.b2.backoffice.domain.board.model.BoardEntity
import java.time.LocalDateTime

data class BoardResponse(
    val id : Int, // 자동 생성
    var createAt : LocalDateTime,
    var title : String,
    var contents : String,
)

