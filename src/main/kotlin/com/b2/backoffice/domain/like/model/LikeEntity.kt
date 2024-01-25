package com.b2.backoffice.domain.like.model



import com.b2.backoffice.domain.post.model.PostEntity
import com.b2.backoffice.domain.user.model.UserEntity
import jakarta.persistence.*
import org.hibernate.annotations.Where

@Entity
@Table(name = "likes")
@Where(clause = "is_deleted = false")
class LikeEntity(

    @ManyToOne
    @JoinColumn(name = "post_id")
    val post: PostEntity,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: UserEntity,

    @Column(name = "is_deleted")
    var isDeleted: Boolean = false

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null
}
