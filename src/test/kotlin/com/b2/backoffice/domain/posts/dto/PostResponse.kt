package com.b2.backoffice.domain.posts.dto

import org.intellij.lang.annotations.Identifier
import java.time.LocalDateTime


@Entity
@Table(name = "posts")
class PostResponse(
    @Column(name = "created_at")
    val createdAt : LocalDateTime = LocalDateTime.now(),

    @Column(name = "nickname")
    var nickname : String,

    @Column(name = "title")
data class PostResponse(
    val id : Long, // 자동 생성
    var createAt : LocalDateTime,
    var title : String,

    @Column(name = "contents")
    var contents : String,

    @Column(name = "likes")
    var likes : Int,

){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Int? = null
}

)
