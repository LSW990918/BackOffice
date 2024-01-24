package com.b2.backoffice.domain.post.model


import com.b2.backoffice.domain.board.model.BoardEntity
import com.b2.backoffice.domain.comment.model.CommentEntity
import com.b2.backoffice.domain.post.dto.PostResponse
import com.b2.backoffice.domain.post.dto.PostWithCommentResponse
import com.b2.backoffice.domain.user.model.UserEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "post")
class PostEntity(
    @Column(name = "created_at")
    val createdAt : LocalDateTime = LocalDateTime.now(),

    @Column(name = "nickname")
    var nickname : String,

    @Column(name = "title")
    var title : String,

    @Column(name = "contents")
    var contents : String,

    @Column(name = "likes")
    var likes : Int,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user : UserEntity,

    @ManyToOne
    @JoinColumn(name = "board_id")
    val board : BoardEntity,

    @OneToMany(
        orphanRemoval = true,
        mappedBy = "post",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var comments : MutableList<CommentEntity> = mutableListOf()
)

{
    @Id
    @GeneratedValue(strategy = GenerationType. IDENTITY)
    val id : Int? = null
}


fun PostEntity.toResponse() : PostResponse {
    return PostResponse(
    id = id!!,
    createAt = createdAt,
    nickname = nickname,
    title = title,
    contents = contents,
    likes = likes,
    )
}


