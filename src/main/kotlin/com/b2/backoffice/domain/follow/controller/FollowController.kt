package com.b2.backoffice.domain.follow.controller

import com.b2.backoffice.domain.board.dto.BoardResponse
import com.b2.backoffice.domain.follow.service.FollowService
import com.b2.backoffice.infra.security.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*


@RequestMapping("/users/{userId}/follows")
@RestController
class FollowController(
    private val followService: FollowService
) {

    @GetMapping
    fun getFollowList(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        ): ResponseEntity<List<BoardResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(followService.getFollowList(userPrincipal.id))
    }

    @PostMapping
    fun follow(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        boardId: Int,
        ): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(followService.follow(userPrincipal.id, boardId))//유저아이디 추가예정
    }

    @PutMapping
    fun unfollow(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        boardId: Int,
        ): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(followService.unfollow(userPrincipal.id, boardId))//유저아이디 추가예정
    }
}