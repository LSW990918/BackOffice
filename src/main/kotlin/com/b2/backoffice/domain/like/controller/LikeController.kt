package com.b2.backoffice.domain.like.controller


import com.b2.backoffice.domain.like.service.LikeService
import com.b2.backoffice.domain.like_count.dto.LikeCountResponse
import com.b2.backoffice.infra.security.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RequestMapping("/posts/{postId}/like")
@RestController
class LikeController(
    private val likeService: LikeService,
) {

    @GetMapping
    fun getLike(@PathVariable postId: Int): ResponseEntity<LikeCountResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(likeService.getLike(postId))
    }

    @PostMapping
    fun createLike(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable postId: Int): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(likeService.createLike(userPrincipal.id, postId))//유저아이디 추가예정
    }

    @PutMapping
    fun deleteLike(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable postId: Int): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(likeService.deleteLike(userPrincipal.id, postId))//유저아이디 추가예정
    }
}