package com.b2.backoffice.domain.post.model


import com.b2.backoffice.domain.board.model.BoardEntity
import com.b2.backoffice.domain.comment.model.CommentEntity
import com.b2.backoffice.domain.like.model.LikeEntity
import com.b2.backoffice.domain.user.model.UserEntity
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import java.time.LocalDateTime

@Entity
@Table(name = "posts")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE posts SET is_deleted = true WHERE id = ?")
@OnDelete(action = OnDeleteAction.CASCADE)
class PostEntity(
    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "nickname")
    var nickname: String,

    @Column(name = "title")
    var title: String,

    @Column(name = "contents")
    var contents : String,

    @Column(name = "is_deleted")
    var is_deleted: Boolean = false,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: UserEntity,

    @ManyToOne
    @JoinColumn(name = "board_id")
    val board: BoardEntity,

    @OneToMany(
        orphanRemoval = true,
        mappedBy = "post",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var comments: MutableList<CommentEntity> = mutableListOf(),

    @OneToMany(
        orphanRemoval = true,
        mappedBy = "post",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var likes: MutableList<LikeEntity> = mutableListOf(),

    @Column(name = "like_count")
    var likeCount: Int = 0

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


