package com.b2.backoffice.domain.board.model

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
    val id : Long? = null
}
