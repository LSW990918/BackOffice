package com.b2.backoffice.user.service

import com.b2.backoffice.user.dto.UserDeleteRequest
import com.b2.backoffice.user.dto.UserLogInRequest
import com.b2.backoffice.user.dto.UserSignUpRequest
import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserService {
    override fun signUp(request: UserSignUpRequest) {
        TODO("Not yet implemented")
    }

    override fun logIn(request: UserLogInRequest) {
        TODO("Not yet implemented")
    }

    override fun logOut() {
        TODO("Not yet implemented")
    }

    override fun deleteUser(request: UserDeleteRequest) {
        TODO("Not yet implemented")
    }
}