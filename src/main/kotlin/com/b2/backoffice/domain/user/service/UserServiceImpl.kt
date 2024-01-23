package com.b2.backoffice.domain.user.service

import com.b2.backoffice.domain.user.model.User
import com.b2.backoffice.domain.user.model.UserRole
import com.b2.backoffice.domain.user.model.toResponse
import com.b2.backoffice.domain.user.repository.UserRepository
import com.b2.backoffice.domain.user.dto.*
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
) : UserService {
    override fun signUp(request: UserSignUpRequest) : UserResponse {
        if (userRepository.existsByEmail(request.email))
            throw IllegalStateException() // "Email Exists"


        return userRepository.save(
            User(
                email = request.email,
                password = request.password, // 암호화 필요
                nickName = request.nickname,
                role = when(request.role.uppercase()){
                    "USER" -> UserRole.USER
                    "MANAGER" -> UserRole.MANAGER
                    else -> throw IllegalStateException("Invalid Role") // "Invalid Role"

                }
            )
        ).toResponse()
    }

    override fun logIn(request: UserLogInRequest) : UserLogInResponse {
        val user = userRepository.findByEmail(request.email)
        //    ?:throw ModelNotFound()

        // 비밀번호 검증

        // 토큰 생성

        TODO("Not yet implemented")
    }

    override fun logOut() {
        TODO("Not yet implemented")
    }

    override fun updateUser(userId: Int, request: UserUpdateRequest): UserResponse {
        val user = userRepository.findByIdOrNull(userId.toLong())
            ?:throw IllegalArgumentException("Invalid id")



        // 비밀번호 검증


        if(user.passwordList == null)
        {
            user.passwordList = listOf( request.newPassword )
        }
        else if(request.newPassword in user.passwordList!!)
        {
            throw IllegalArgumentException("password already in") // 이미 사용중인 패스워드 예외 처리
        }
        else if(user.passwordList!!.size < 3)
        {
            user.passwordList = listOf(request.newPassword) + user.passwordList!!
        }
        else
        {
            user.passwordList = user.passwordList!!.drop(1).plus(request.newPassword)
        }

        user.nickName = request.nickName
        return userRepository.save(user).toResponse()
    }

    override fun deleteUser(userId : Int) {
        val user = userRepository.findByIdOrNull(userId.toLong())
            ?:throw IllegalArgumentException()

        // 비밀번호 검증

        userRepository.delete(user)
    }
}