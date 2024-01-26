package com.b2.backoffice.domain.board.model

import com.b2.backoffice.domain.post.model.PostEntity
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import java.time.LocalDateTime

@Entity
@SQLDelete(sql = "UPDATE boards SET is_deleted = true WHERE id = ?") // DELETE 쿼리 날아올 시 대신 실행
@Where(clause = "is_deleted = false")
@OnDelete(action = OnDeleteAction.CASCADE)
@Table(name = "boards")
class BoardEntity(
    @Column(name = "created_at")
    val createdAt : LocalDateTime = LocalDateTime.now(),

    @Column(name = "is_deleted")
    var isDeleted : Boolean = false,

    @Column(name = "title")
    var title : String,

    @Column(name = "contents")
    var contents : String,

    @OneToMany(
        orphanRemoval = true,
        mappedBy = "board",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var posts : MutableList<PostEntity> = mutableListOf()
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Int? = null

    fun createPost(post:PostEntity){
        posts.add(post)
    }
    fun deletePost(post:PostEntity){
        posts.remove(post)
    }
}

