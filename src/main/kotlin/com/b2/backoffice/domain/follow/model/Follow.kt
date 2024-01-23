package com.b2.backoffice.domain.follow.model

import jakarta.persistence.*

@Entity
@Table(name = "follow")
class Follow(

    //@ManyToOne
    //@JoinColumn(name = "board_id")
    //val board: Board,

    //@ManyToOne
    //@JoinColumn(name = "user_id")
    //val user: User

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null
}