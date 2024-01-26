package com.b2.backoffice.domain.user.service

import com.b2.backoffice.domain.exception.ModelNotFoundException
import com.b2.backoffice.domain.user.model.UserEntity
import com.b2.backoffice.domain.user.model.UserRole
import com.b2.backoffice.domain.user.repository.UserRepository
import com.b2.backoffice.domain.user.dto.*
import com.b2.backoffice.infra.security.SecurityService
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
    private val securityService: SecurityService,
    private val jwtPlugin: JwtPlugin,
) : UserService {

    @Transactional
    override fun signUp(request: UserSignUpRequest): UserResponse {
        if (userRepository.existsByEmail(request.email))
            throw IllegalStateException("Email already Exists")

        var pw = passwordEncoder.encode(request.password)

        return userRepository.save(
            UserEntity(
                email = request.email,
                password = pw,
                passwordList = mutableListOf(pw),
                nickName = request.nickName,
                role = when (request.role.uppercase()) {
                    "USER" -> UserRole.USER
                    "MANAGER" -> UserRole.MANAGER
                    "ADMIN" -> UserRole.ADMIN
                    else -> throw IllegalStateException("Invalid Role")
                }
            )
        ).toResponse()
    }

    override fun logIn(request: UserLogInRequest): UserLogInResponse {
        val user = userRepository.findByEmail(request.email)
            ?: throw IllegalArgumentException("Invalid email") //ModelNotFound() 추가 필요

        securityService.chkPassword(request.password, user.password)

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

    override fun getUserList(): List<UserResponse> {

        return userRepository.findAll().map { it.toResponse() }
            ?: throw IllegalArgumentException("Invalid id")
    }

    // my profile 과 관리자모드 유저프로파일 분리 ?
    override fun getUser(userPrincipal: UserPrincipal, userId: Int): UserResponse {

        securityService.chkUserId(userPrincipal, userId)

        return userRepository.findByIdOrNull(userId)
            ?.toResponse()
            ?: throw throw ModelNotFoundException("User", userId)
    }


    override fun setUser(userPrincipal: UserPrincipal, userId: Int) : UserResponse?{
        val user = userRepository.findByIdOrNull(userId)
            ?: throw ModelNotFoundException("User", userId)
        TODO()
    }

    @Transactional
    override fun updateUser(userPrincipal: UserPrincipal, userId: Int, request: UserUpdateRequest): UserResponse {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw ModelNotFoundException("User", userId)

        securityService.chkUserId(userPrincipal, userId)

        val newPassword = passwordEncoder.encode(request.newPassword)

        securityService.chkPassword(request.password, user.password)

        for (i in user.passwordList) {
            if (passwordEncoder.matches(request.newPassword, i))
                throw IllegalArgumentException("password already used")
        }

        if (user.passwordList.size < 3) {
            user.passwordList.add(newPassword)
        } else {
            user.passwordList.add(newPassword)
            user.passwordList.removeAt(0)
        }

        user.password = newPassword
        user.nickName = request.nickName
        return userRepository.save(user).toResponse()
    }

    @Transactional
    override fun deleteUser(userPrincipal: UserPrincipal, userId: Int, password: String) {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw ModelNotFoundException("User", userId)


        securityService.chkPassword(password, user.password)

        user.isDeleted = true

        userRepository.save(user)
    }
}


fun UserEntity.toResponse(): UserResponse {
    return UserResponse(
        id = id!!,
        createAt = createdAt,
        email = email,
        nickName = nickName,
        role = role.name
    )
}