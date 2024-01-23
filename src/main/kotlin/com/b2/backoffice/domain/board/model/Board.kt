package com.b2.backoffice.domain.board.model

import com.b2.backoffice.domain.board.dto.BoardResponse
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "boards")
class Board(
    @Column(name = "created_at")
    val createdAt : LocalDateTime = LocalDateTime.now(),

    @Column(name = "title")
    var title : String,

    @Column(name = "contents")
    var contents : String,
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Int? = null
}

fun Board.toResponse() : BoardResponse
{
    return BoardResponse(
        id = id!!,
        createAt = createdAt,
        title = title,
        contents = contents
    )
}