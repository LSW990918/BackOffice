package com.b2.backoffice.user.service

import com.b2.backoffice.user.dto.*

interface UserService {
    fun signUp(request : UserSignUpRequest) : UserResponse
    fun logIn(request : UserLogInRequest) : UserLogInResponse

    fun logOut()

    fun updateUser(userId : Int, request : UserUpdateRequest) : UserResponse
    fun deleteUser(userId : Int, request: UserDeleteRequest)
}