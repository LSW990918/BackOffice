package com.b2.backoffice.domain.user.service

import com.b2.backoffice.domain.user.model.UserEntity
import com.b2.backoffice.domain.user.model.UserRole
import com.b2.backoffice.domain.user.repository.UserRepository
import com.b2.backoffice.domain.user.dto.*
import com.b2.backoffice.infra.security.UserPrincipal
import com.b2.backoffice.infra.security.jwt.JwtPlugin
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin,
) : UserService {

    @Transactional
    override fun signUp(request: UserSignUpRequest) : UserResponse {
        if (userRepository.existsByEmail(request.email))
            throw IllegalStateException("Email Exists")

        var pw = passwordEncoder.encode(request.password)

        return userRepository.save(
            UserEntity(
                email = request.email,
                password = pw,
                passwordList = mutableListOf( pw ),
                nickName = request.nickname,
                role = when(request.role.uppercase()){
                    "USER" -> UserRole.USER
                    "MANAGER" -> UserRole.MANAGER
                    "ADMIN" -> UserRole.ADMIN
                    else -> throw IllegalStateException("Invalid Role")
                }
            )
        ).toResponse()
    }

    override fun logIn(request: UserLogInRequest) : UserLogInResponse {
        val user = userRepository.findByEmail(request.email)
            ?:throw  IllegalArgumentException("Invalid email") //ModelNotFound() 추가 필요

        chkPassword(request.password, user.password)

        // 토큰 생성
        return UserLogInResponse(
            accessToken = jwtPlugin.generateAccessToken(
                subject = user.id.toString(),
                email = user.email,
                role = user.role.name
            )
        )
    }

    override fun logOut() {
        TODO("Not yet implemented")
    }

    override fun getUserList() : List<UserResponse> {

        return userRepository.findAll().map{ it.toResponse() }
            ?:throw IllegalArgumentException("Invalid id")
    }

    // my profile 과 관리자모드 유저프로파일 분리 ?
    override fun getUser(userId: Int) : UserResponse {
        return userRepository.findByIdOrNull(userId)
            ?.toResponse()
            ?:throw IllegalArgumentException("Invalid id")
    }

    override fun updateUser(userPrincipal: UserPrincipal, userId: Int, request: UserUpdateRequest): UserResponse {

        println(userPrincipal.authoricies)

        val user = userRepository.findByIdOrNull(userId)
            ?:throw IllegalArgumentException("Invalid id")


        chkPassword(request.password, user.password)

        var newPassword = passwordEncoder.encode(request.newPassword)

        for(i in user.passwordList)
        {
            if ( passwordEncoder.matches(request.newPassword , i) )
                throw IllegalArgumentException("password already used")
        }

        if(user.passwordList.size < 3)
        {
            user.passwordList.add(newPassword)
        }
        else
        {
            user.passwordList.add(newPassword)
            user.passwordList.removeAt(0)
        }

        user.password = newPassword
        user.nickName = request.nickName
        return userRepository.save(user).toResponse()
    }

    override fun deleteUser(userPrincipal: UserPrincipal, userId : Int, password: String) {
        val user = userRepository.findByIdOrNull(userId)
            ?:throw IllegalArgumentException()

        chkPassword(password, user.password)

         // InvalidCredentialException 으로 변경 필요

        userRepository.delete(user)
    }

    fun chkPassword(rawPw: String, encodePw: String) {
        if(!passwordEncoder.matches(rawPw, encodePw))
            throw IllegalArgumentException("Invalid password")

    }
}



fun UserEntity.toResponse() : UserResponse {
    return UserResponse(
        id = id!!,
        createAt = createdAt,
        email = email,
        nickName = nickName,
        role = role.name
    )
}