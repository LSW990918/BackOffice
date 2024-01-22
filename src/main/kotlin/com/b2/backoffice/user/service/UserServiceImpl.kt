package com.b2.backoffice.user.service

import com.b2.backoffice.user.dto.*
import com.b2.backoffice.user.model.User
import com.b2.backoffice.user.model.UserRole
import com.b2.backoffice.user.model.toResponse
import com.b2.backoffice.user.repository.UserRepository
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
                    else -> throw IllegalStateException() // "Invalid Role"
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

    override fun updateUser(userId: Long, request: UserUpdateRequest): UserResponse {
        val user = userRepository.findByIdOrNull(userId)
            ?:throw IllegalArgumentException()

        // 비밀번호 검증

        user.nickName = request.nickName
        return userRepository.save(user).toResponse()
    }

    override fun deleteUser(userId : Long, request: UserDeleteRequest) {
        val user = userRepository.findByIdOrNull(userId)
            ?:throw IllegalArgumentException()

        // 비밀번호 검증

        userRepository.delete(user)
    }
}