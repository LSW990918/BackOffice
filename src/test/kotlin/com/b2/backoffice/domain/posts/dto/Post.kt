package com.b2.backoffice.domain.posts.dto

class Post {
import java.time.LocalDateTime
@Entity
@Table(name = "posts")
class Entity(
    @Column(name = "create_at")
    val createdAt : LocalDateTime = LocalDateTime.now(),

    @Column(name = "nickname")
    var nickname : String,

    @Column(name = "title")
    var title : String,

    @Column(name = "contents")
    var contents : String,

    @Column(name = "likes")
    var likes : Int

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Int? = null
}
