package com.b2.backoffice.user.service

import com.b2.backoffice.user.dto.*

interface UserService {
    fun signUp(request : UserSignUpRequest) : UserResponse
    fun logIn(request : UserLogInRequest) : UserLogInResponse

    fun logOut()

    fun updateUser(userId : Long, request : UserUpdateRequest) : UserResponse
    fun deleteUser(userId : Long, request: UserDeleteRequest)
}