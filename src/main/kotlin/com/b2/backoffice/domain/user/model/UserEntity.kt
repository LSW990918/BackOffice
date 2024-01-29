package com.b2.backoffice.domain.user.model

import com.b2.backoffice.domain.board.model.BoardEntity
import com.b2.backoffice.domain.comment.model.CommentEntity
import com.b2.backoffice.domain.post.model.PostEntity
import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import java.time.LocalDateTime

@Entity
@Table(name = "app_users")
@SQLDelete(sql = "UPDATE boards SET is_deleted = true WHERE id = ?") // DELETE 쿼리 날아올 시 대신 실행
@Where(clause = "is_deleted = false")
class UserEntity(
    @Column(name = "created_at")
    val createdAt : LocalDateTime = LocalDateTime.now(),

    @Column(name = "modified_at")
    var modifiedAt : LocalDateTime? = null,

    @Column(name = "is_deleted")
    var isDeleted : Boolean = false,

    @Column(name = "email")
    val email : String,

    @Column(name = "password")
    var password : String,

    @ElementCollection
    @Column(name = "password_list")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    var passwordList: MutableList<String>,


    @Column(name = "nickname")
    var nickName : String,

    @Column(name = "introduce")
    var introduce : String,

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    var role : UserRole,

    @OneToMany(
        orphanRemoval = true,
        mappedBy = "user",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var posts : MutableList<PostEntity> = mutableListOf(),

    @OneToMany(
        orphanRemoval = true,
        mappedBy = "user",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var comments : MutableList<CommentEntity> = mutableListOf()
    )
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Int? = null
    fun createPost(post:PostEntity){
        posts.add(post)
    }
    fun deletePost(post:PostEntity){
        posts.remove(post)
    }

    fun createComment(comment:CommentEntity){
        comments.add(comment)
    }
    fun deletePost(comment:CommentEntity){
        comments.remove(comment)
    }


}