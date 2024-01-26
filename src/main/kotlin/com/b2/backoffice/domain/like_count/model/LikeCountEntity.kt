//package com.b2.backoffice.domain.like_count.model
//
//import com.b2.backoffice.domain.post.model.PostEntity
//import jakarta.persistence.*
//import org.hibernate.annotations.OnDelete
//import org.hibernate.annotations.OnDeleteAction
//import org.hibernate.annotations.SQLDelete
//import org.hibernate.annotations.Where
//
//@Entity
//@Table(name = "like_count")
//@Where(clause = "is_deleted = false")
//@SQLDelete(sql = "UPDATE likes SET is_deleted = true WHERE id = ?")
//@OnDelete(action = OnDeleteAction.CASCADE)
//class LikeCountEntity(
//
//    @OneToOne(
//        orphanRemoval = true,
//        fetch = FetchType.LAZY,
//        cascade = [CascadeType.ALL]
//    )
//    @JoinColumn(name = "post_id")
//    val post: PostEntity,
//
//    @Column(name = "like_count")
//    var likeCount: Int = 0,
//
//    @Column(name = "is_deleted")
//    val isDeleted: Boolean = false
//
//) {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    val id: Int? = null
//
//    fun increaseLikeCount() {
//        likeCount++
//    }
//    fun decreaseLikeCount() {
//        likeCount--
//    }
//
//}
