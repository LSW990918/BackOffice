package com.b2.backoffice.domain.comment.controller

import com.b2.backoffice.domain.comment.dto.*
import com.b2.backoffice.domain.comment.service.CommentService
import com.b2.backoffice.domain.post.service.PostService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/posts")
class CommentController(
    private val commentService: CommentService  // 댓글 서비스를 주입받는다
) {

    // 댓글 추가 엔드포인트
    @PostMapping("/{postId}/comments/{commentId}")
    fun createComment(
        @PathVariable
        postId: Int,
        userId: Int,
        @RequestBody commentCreateRequest: CommentCreateRequest  // 요청 본문에서 댓글 추가를 위한 정보를 받음
    ): ResponseEntity<CommentResponse> {
        // HTTP 상태 코드 201(Created)로 응답하고, 댓글 추가 서비스 호출
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentService.createComment(postId, userId, commentCreateRequest))
    }

    // 댓글 수정 엔드포인트
    @PutMapping("/{postId}/comments/{commentId}")
    fun updateComment(
        @PathVariable
        postId: Int, // 포스트 ID를 경로 변수로 받음
        userId: Int,
        commentId: Int,                 // 댓글 ID를 경로 변수로 받음
        @RequestBody commentUpdateRequest: CommentUpdateRequest  // 요청 본문에서 댓글 수정을 위한 정보를 받음
    ): ResponseEntity<CommentResponse> {
        // HTTP 상태 코드 200(OK)로 응답하고, 댓글 수정 서비스 호출
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.updateComment(postId, commentId, userId, commentUpdateRequest))
    }

    // 댓글 삭제 엔드포인트
    @DeleteMapping("/{postId}/comments/{commentId}")
    fun deleteComment(
        userId: Int,
        @PathVariable
        postId: Int,        // 포스트 ID를 경로 변수로 받음
        @PathVariable
        commentId: Int      // 댓글 ID를 경로 변수로 받음
    ): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(commentService.deleteComment(userId,postId, commentId))
    }

    @GetMapping("/{postId}/comments")
    fun getCommentByPostId() {
        TODO()
    }

    @GetMapping("/comments")
    fun getAllComments() {
        TODO()
    }

}