package com.b2.backoffice.domain.like.controller


import com.b2.backoffice.domain.like.dto.LikeResponse
import com.b2.backoffice.domain.postslike.service.LikeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/posts/{postId}/like")
@RestController
class LikeController(
    private val likeService: LikeService
) {

    @GetMapping
    fun getLike(@PathVariable postId: Int): ResponseEntity<LikeResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(likeService.getLike(postId))
    }

    @PostMapping
    fun createLike(@PathVariable postId: Int): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(likeService.createLike(1,postId))//유저아이디 추가예정
    }

    @DeleteMapping
    fun deleteLike(@PathVariable postId: Int): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(likeService.deleteLike(1, postId))//유저아이디 추가예정
    }
}