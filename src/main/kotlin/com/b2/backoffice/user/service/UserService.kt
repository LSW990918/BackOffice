package com.b2.backoffice.user.service

import com.b2.backoffice.user.dto.UserDeleteRequest
import com.b2.backoffice.user.dto.UserLogInRequest
import com.b2.backoffice.user.dto.UserSignUpRequest

interface UserService {
    fun signUp(request : UserSignUpRequest)
    fun logIn(request : UserLogInRequest)
    fun logOut()
    fun deleteUser(request: UserDeleteRequest)
}