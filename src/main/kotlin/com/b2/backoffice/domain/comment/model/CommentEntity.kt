package com.b2.backoffice.domain.comment.model

import com.b2.backoffice.domain.post.model.PostEntity
import com.b2.backoffice.domain.user.model.UserEntity
import jakarta.persistence.*
import java.time.LocalDateTime
import org.hibernate.annotations.Where

@Entity
@Where(clause = "is_deleted = false")
@Table(name = "comments")
class CommentEntity(
    @Column(name = "create_at", nullable = false)
    var createAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "content", nullable = false)
    var content: String,

    @Column(name = "nickname", nullable = false)
    var nickName: String,

    @Column(name = "userid", nullable = false)
    var userId: Int,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    var user: UserEntity,


    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    var post: PostEntity,

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentid")
    var id: Int? = null

}


