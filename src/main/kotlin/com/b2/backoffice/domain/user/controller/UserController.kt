package com.b2.backoffice.domain.user.controller

import com.b2.backoffice.domain.board.dto.BoardResponse
import com.b2.backoffice.domain.comment.dto.CommentResponse
import com.b2.backoffice.domain.post.dto.PostResponse
import com.b2.backoffice.domain.user.service.UserService
import com.b2.backoffice.domain.user.dto.*
import com.b2.backoffice.infra.security.UserPrincipal
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
@Validated
class UserController(
    private val userService: UserService
) {
    @PostMapping("/users/signUp")
    fun signUp(
        @Valid @RequestBody request : UserSignUpRequest,
    ) : ResponseEntity<UserResponse>
    {
      return ResponseEntity.status(HttpStatus.CREATED).body(userService.signUp(request))
    }

    @PostMapping("/logIn")
    fun logIn(
        @Valid @RequestBody request : UserLogInRequest
    ) : ResponseEntity<UserLogInResponse>
    {
        return ResponseEntity.status(HttpStatus.OK).body(userService.logIn(request))
    }

    @GetMapping("/logOut")
    fun logOut(
        @AuthenticationPrincipal userPrincipal: UserDetails?,
        request: HttpServletRequest,
        response: HttpServletResponse
    ): ResponseEntity<Unit> {
        if (userPrincipal != null) {
            // 여기에서 로그아웃 처리 로직을 수행
            val auth: Authentication = SecurityContextHolder.getContext().authentication
            SecurityContextLogoutHandler().logout(request, response, auth)
            return ResponseEntity.status(HttpStatus.OK).build()
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
    }

    @GetMapping("/{userId}/profile")
    fun getUser(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable userId : Int
    ) : ResponseEntity<UserResponse>
    {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(userPrincipal, userId))
    }

    @PutMapping("/{userId}/profile")
    fun updateUser(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @Valid @RequestBody request: UserUpdateRequest,
        @PathVariable userId: Int,
    ): ResponseEntity<UserResponse>
    {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(userPrincipal,userId, request))
    }
    @DeleteMapping("/{userId}")
    fun deleteUser(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        password : String,
        @PathVariable userId: Int,
    ): ResponseEntity<Unit>
    {
        userService.deleteUser(userPrincipal,userId, password)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/getAllUsers")
    fun getAllUsers(
    ) : ResponseEntity<List<UserResponse>>
    {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUserList())
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/getAllBoards")
    fun getAllBoards(
    ) : ResponseEntity<List<BoardResponse>>
    {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllBoardList())
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/getAllPosts")
    fun getAllPosts(
    ) : ResponseEntity<List<PostResponse>>
    {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllPostList())
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/getAllComments")
    fun getAllComments(
    ) : ResponseEntity<List<CommentResponse>>
    {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllCommentList())
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{userId}/resetPassword")
    fun resetPassword(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable userId : Int
    ) : ResponseEntity<UserResponse>
    {
        return ResponseEntity.status(HttpStatus.OK).body(userService.resetPassword(userPrincipal, userId))
    }
}