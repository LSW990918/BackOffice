package com.b2.backoffice.domain.post.controller

import com.b2.backoffice.domain.post.dto.PostCreateRequest
import com.b2.backoffice.domain.post.dto.PostResponse
import com.b2.backoffice.domain.post.dto.PostUpdateRequest
import com.b2.backoffice.domain.post.service.PostService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/boards/{boardId}/post")
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
        @PathVariable boardId: Int,
        @RequestBody request: PostCreateRequest
    ): ResponseEntity<PostResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(postService.createPost(boardId, request))
    }

    @PutMapping("/{postId}")
    fun updatePost(
        @PathVariable boardId: Int,
        @PathVariable postId: Int,
        @RequestBody request: PostUpdateRequest
    ): ResponseEntity<PostResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(postService.updatePost(boardId, postId, request))
    }
}

