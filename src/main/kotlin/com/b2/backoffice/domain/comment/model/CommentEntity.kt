package com.b2.backoffice.domain.comment.model

import com.b2.backoffice.domain.post.model.PostEntity
import com.b2.backoffice.domain.user.model.UserEntity
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import java.time.LocalDateTime

@Entity
@Table(name = "comments")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE comments SET is_deleted = true WHERE id = ?")
@OnDelete(action = OnDeleteAction.CASCADE)
class CommentEntity(
    @Column(name = "create_at", nullable = false)
    var createAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "content", nullable = false)
    var content: String,

    @Column(name = "nickname", nullable = false)
    var nickName: String,

    @Column(name = "is_deleted", nullable = false)
    var isDelete: Boolean = false,

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


