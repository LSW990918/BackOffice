package com.b2.backoffice.domain.board.model

import com.b2.backoffice.domain.board.dto.BoardResponse
import com.b2.backoffice.domain.post.model.PostEntity
import com.b2.backoffice.domain.user.dto.UserResponse
import com.b2.backoffice.domain.user.model.UserEntity
import jakarta.persistence.*
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where

import java.time.LocalDateTime

@Entity
@SQLDelete(sql = "UPDATE boards SET is_deleted = true WHERE id = ?") // DELETE 쿼리 날아올 시 대신 실행
@Where(clause = "is_deleted = false")
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
}

