package domain.comment.controller

import com.b2.backoffice.domain.comment.dto.*
import com.b2.backoffice.domain.comment.service.CommentService
import com.b2.backoffice.domain.post.service.PostService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/posts/{postId}/comments")
class CommentController(
    private val postService: PostService,      // 포스트 서비스를 주입받는다
    private val commentService: CommentService  // 댓글 서비스를 주입받는다
) {

    // 댓글 추가 엔드포인트
    @PostMapping
    fun createComment(
        @PathVariable
        postId: Int,
        userId: Int,
        @RequestBody createCommentRequest: CreateCommentRequest  // 요청 본문에서 댓글 추가를 위한 정보를 받음
    ): ResponseEntity<CommentResponse> {
        // HTTP 상태 코드 201(Created)로 응답하고, 댓글 추가 서비스 호출
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentService.createComment(postId, userId, createCommentRequest))
    }

    // 댓글 수정 엔드포인트
    @PutMapping("/{commentId}")
    fun updateComment(
        @PathVariable
        postId: Int, // 포스트 ID를 경로 변수로 받음
        userId: Int,
        commentId: Int,                 // 댓글 ID를 경로 변수로 받음
        @RequestBody updateCommentRequest: UpdateCommentRequest  // 요청 본문에서 댓글 수정을 위한 정보를 받음
    ): ResponseEntity<CommentResponse> {
        // HTTP 상태 코드 200(OK)로 응답하고, 댓글 수정 서비스 호출
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.updateComment(postId, commentId, userId, updateCommentRequest))
    }

    // 댓글 삭제 엔드포인트
    @DeleteMapping("/{commentId}")
    fun deleteComment(
        userId: Int,
        @PathVariable
        postId: Int,        // 포스트 ID를 경로 변수로 받음
        @PathVariable
        commentId: Int      // 댓글 ID를 경로 변수로 받음
    ): ResponseEntity<Unit> {
        // 댓글 삭제 서비스 호출 후 성공 메시지 반환
        commentService.deleteComment(userId,postId, commentId)
        val deleteCommentSuccessMessage = "댓글이 성공적으로 삭제되었습니다."
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
}
