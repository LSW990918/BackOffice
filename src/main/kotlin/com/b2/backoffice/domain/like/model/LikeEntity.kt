package com.b2.backoffice.domain.like.model



import com.b2.backoffice.domain.post.model.PostEntity
import com.b2.backoffice.domain.user.model.UserEntity
import jakarta.persistence.*

@Entity
@Table(name = "likes")
class LikeEntity(

    @ManyToOne
    @JoinColumn(name = "post_id")
    val post: PostEntity,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: UserEntity

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null
}
