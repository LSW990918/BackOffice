package com.b2.backoffice.domain.user.service

import com.b2.backoffice.domain.user.dto.*
import com.b2.backoffice.infra.security.UserPrincipal

interface UserService {
    fun signUp(request : UserSignUpRequest) : UserResponse
    fun logIn(request : UserLogInRequest) : UserLogInResponse

    fun logOut()

    fun getUserList() : List<UserResponse>?
    fun getUser(userPrincipal: UserPrincipal, userId: Int) : UserResponse?

    fun updateUser(userPrincipal: UserPrincipal, userId : Int, request : UserUpdateRequest) : UserResponse
    fun deleteUser(userPrincipal: UserPrincipal, userId : Int, password: String)
}