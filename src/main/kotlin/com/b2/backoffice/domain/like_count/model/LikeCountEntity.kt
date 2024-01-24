package com.b2.backoffice.domain.like_count.model

import com.b2.backoffice.domain.post.model.PostEntity
import jakarta.persistence.*

@Entity
@Table(name = "like")
class LikeCountEntity(

    @OneToOne
    @JoinColumn(name = "post_id")
    val post: PostEntity,

    @Column(name = "like_cnt")
    var likeCount: Int

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null

    fun increaseLikeCount(likeCountEntity: LikeCountEntity) {
        likeCount++
    }
    fun decreaseLikeCount(likeCountEntity: LikeCountEntity) {
        likeCount--
    }

}
