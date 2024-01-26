package com.b2.backoffice.domain.post.controller

import com.b2.backoffice.domain.post.dto.PostCreateRequest
import com.b2.backoffice.domain.post.dto.PostResponse
import com.b2.backoffice.domain.post.dto.PostUpdateRequest
import com.b2.backoffice.domain.post.service.PostService
import com.b2.backoffice.infra.security.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/boards/{boardId}/posts")
class PostController(
    private var postService: PostService
) {

    @GetMapping()
    fun getPostList(
        @PathVariable boardId: Int
    ): ResponseEntity<List<PostResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(postService.getPostList(boardId))
    }

    @GetMapping("/{postId}")
    fun getPost(
        @PathVariable boardId: Int,
        @PathVariable postId: Int
    ): ResponseEntity<PostResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(postService.getPost(boardId, postId))
    }

    @PostMapping()
    fun createPost(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable boardId: Int,
        @RequestBody request: PostCreateRequest
    ): ResponseEntity<PostResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(postService.createPost(boardId, userPrincipal, request))
    }

    @PutMapping("/{postId}/update")
    fun updatePost(
        @PathVariable boardId: Int,
        @PathVariable postId: Int,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody request: PostUpdateRequest
    ): ResponseEntity<PostResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(postService.updatePost(boardId, postId, userPrincipal, request))
    }

    @DeleteMapping("/{postId}/delete")
    fun deletePost(
        @PathVariable boardId: Int,
        @PathVariable postId: Int,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        ) : ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(postService.deletePost(boardId, postId, userPrincipal))
    }
}