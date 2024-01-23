package com.b2.backoffice.domain.like.model


import jakarta.persistence.*

@Entity
@Table(name = "like")
class Like(

    //@ManyToOne
    //@JoinColumn(name = "post_id")
    //val post: Post,

    //@ManyToOne
    //@JoinColumn(name = "user_id")
    //val user: User

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null
}
