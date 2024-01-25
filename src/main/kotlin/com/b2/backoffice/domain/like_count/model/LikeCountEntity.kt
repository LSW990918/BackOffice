package com.b2.backoffice.domain.like_count.model

import com.b2.backoffice.domain.post.model.PostEntity
import jakarta.persistence.*
import org.hibernate.annotations.Where

@Entity
@Table(name = "like")
@Where(clause = "is_deleted = false")
class LikeCountEntity(

    @OneToOne
    @JoinColumn(name = "post_id")
    val post: PostEntity,

    @Column(name = "like_count")
    var likeCount: Int,

    @Column(name = "is_deleted")
    val isDeleted: Boolean = false

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null

    fun increaseLikeCount() {
        likeCount++
    }
    fun decreaseLikeCount() {
        likeCount--
    }

}
