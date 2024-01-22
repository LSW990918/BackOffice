package com.b2.backoffice.user.controller

import com.b2.backoffice.user.dto.UserLogInRequest
import com.b2.backoffice.user.dto.UserResponse
import com.b2.backoffice.user.dto.UserSignUpRequest
import com.b2.backoffice.user.dto.UserUpdateRequest
import com.b2.backoffice.user.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService
) {
    @PostMapping("/signUp")
    fun signUp(
        @RequestBody request : UserSignUpRequest
    ) : ResponseEntity<UserResponse>
    {
      TODO()
    }

    @PostMapping("/logIn")
    fun logIn(
        @RequestBody request : UserLogInRequest
    ) : ResponseEntity<UserResponse>
    {
        TODO()
    }

    @PutMapping("/{userId}/profile")
    fun updateUser(
        @RequestBody request: UserUpdateRequest,
        @PathVariable userId: Long,
    ): ResponseEntity<UserResponse>
    {
        TODO()
    }
}