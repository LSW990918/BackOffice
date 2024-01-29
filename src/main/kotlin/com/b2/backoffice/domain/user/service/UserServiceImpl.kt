package com.b2.backoffice.domain.user.service

import com.b2.backoffice.domain.board.dto.BoardResponse
import com.b2.backoffice.domain.board.repository.BoardRepository
import com.b2.backoffice.domain.board.service.toResponse
import com.b2.backoffice.domain.comment.dto.CommentResponse
import com.b2.backoffice.domain.comment.repository.CommentRepository
import com.b2.backoffice.domain.comment.service.toResponse
import com.b2.backoffice.domain.exception.ModelNotFoundException
import com.b2.backoffice.domain.post.dto.PostResponse
import com.b2.backoffice.domain.post.repository.PostRepository
import com.b2.backoffice.domain.post.service.toResponse
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
import java.time.LocalDateTime

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val boardRepository: BoardRepository,
    private val postRepository: PostRepository,
    private val commentRepository: CommentRepository,
    private val passwordEncoder: PasswordEncoder,
    private val securityService: SecurityService,
    private val jwtPlugin: JwtPlugin,
) : UserService {


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
                introduce = request.introduce,
                role = when (request.role.uppercase()) {
                    "USER" -> UserRole.USER
                    //"MANAGER" -> UserRole.MANAGER
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

    // my profile 과 관리자모드 유저프로파일 분리 ?
    override fun getUser(userPrincipal: UserPrincipal, userId: Int): UserResponse {
        return userRepository.findByIdOrNull(userId)
            ?.toResponse()
            ?: throw throw ModelNotFoundException("User", userId)
    }


    override fun getAllUserList(): List<UserResponse>? {
        return userRepository.findAll().map { it.toResponse() }
    }
    override fun getAllBoardList(): List<BoardResponse>? {
        return boardRepository.findAll().map { it.toResponse() }
    }
    override fun getAllPostList(): List<PostResponse>? {
        return postRepository.findAll().map { it.toResponse() }
    }
    override fun getAllCommentList(): List<CommentResponse> {
        return commentRepository.findAll().map { it.toResponse() }
    }


    @Transactional
    override fun resetPassword(userPrincipal: UserPrincipal, userId: Int): UserResponse {
        var user = userRepository.findByIdOrNull(userId)
            ?: throw ModelNotFoundException("User", userId)

        val charPool = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        val randPw =  (1..8)
            .map { charPool.random() }
            .joinToString("")

        user = updatePassword(user, randPw)

        user.modifiedAt = LocalDateTime.now()
        userRepository.save(user)

        return user.toResponse()
    }

    @Transactional
    override fun updateUser(userPrincipal: UserPrincipal, userId: Int, request: UserUpdateRequest): UserResponse {
        var user = userRepository.findByIdOrNull(userId)
            ?: throw ModelNotFoundException("User", userId)

        securityService.chkUserId(userPrincipal, userId) // ADIMIN 이거나 USER 본인 인지 확인

        securityService.chkPassword(request.password, user.password) // 현재 로그인 된 사용자 비밀번호 재확인

        user = updatePassword(user, request.newPassword)

        user.nickName = request.nickName
        user.introduce = request.introduce

        user.modifiedAt = LocalDateTime.now()
        return userRepository.save(user).toResponse()
    }

    @Transactional
    override fun deleteUser(userPrincipal: UserPrincipal, userId: Int, password: String) {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw ModelNotFoundException("User", userId)

        securityService.chkUserId(userPrincipal,userId)
        securityService.chkPassword(password, user.password)

        user.modifiedAt = LocalDateTime.now()
        userRepository.save(user)
        userRepository.delete(user)
    }

    fun updatePassword(user : UserEntity, requestPw : String) : UserEntity
    {
        for (i in user.passwordList) {
            if (passwordEncoder.matches(requestPw, i))
                throw IllegalArgumentException("password already used")
        }

        val newPw = passwordEncoder.encode(requestPw)

        if (user.passwordList.size < 3) {
            user.passwordList.add(newPw)
        }
        else {
            user.passwordList.add(newPw)
            user.passwordList.removeAt(0)
        }

        user.password = newPw

        return user
    }
}


fun UserEntity.toResponse(): UserResponse {
    return UserResponse(
        id = id!!,
        createAt = createdAt,
        modifiedAt = modifiedAt,
        email = email,
        nickName = nickName,
        introduce = introduce,
        role = role.name
    )
}