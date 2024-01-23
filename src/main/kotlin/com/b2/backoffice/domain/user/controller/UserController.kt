package com.b2.backoffice.domain.user.controller

import com.b2.backoffice.domain.user.service.UserService
import com.b2.backoffice.domain.user.dto.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.swing.text.PasswordView

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
      return ResponseEntity.status(HttpStatus.CREATED).body(userService.signUp(request))
    }

    @PostMapping("/logIn")
    fun logIn(
        @RequestBody request : UserLogInRequest
    ) : ResponseEntity<UserLogInResponse>
    {
        return ResponseEntity.status(HttpStatus.OK).body(userService.logIn(request))
    }

    @PutMapping("/{userId}/profile")
    fun updateUser(
        @RequestBody request: UserUpdateRequest,
        @PathVariable userId: Int,
    ): ResponseEntity<UserResponse>
    {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(userId, request))
    }
    @DeleteMapping("/{userId}")
    fun deleteUser(
        password : String,
        @PathVariable userId: Int,
    ): ResponseEntity<Unit>
    {
        userService.deleteUser(userId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}