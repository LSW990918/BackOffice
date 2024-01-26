package com.b2.backoffice.domain.user.controller

import com.b2.backoffice.domain.user.service.UserService
import com.b2.backoffice.domain.user.dto.*
import com.b2.backoffice.infra.security.UserPrincipal
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.validation.BindingResult
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
    @PostMapping("/signUp")
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


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    fun getUserList(
    ) : ResponseEntity<List<UserResponse>>
    {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserList())
    }

    @GetMapping("/{userId}/profile")
    fun getUser(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable userId : Int
    ) : ResponseEntity<UserResponse>
    {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(userPrincipal, userId))
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
}