package com.b2.backoffice.domain.user.service

import com.b2.backoffice.domain.board.dto.BoardResponse
import com.b2.backoffice.domain.comment.dto.CommentResponse
import com.b2.backoffice.domain.post.dto.PostResponse
import com.b2.backoffice.domain.user.dto.*
import com.b2.backoffice.infra.security.UserPrincipal

interface UserService {
    fun signUp(request : UserSignUpRequest) : UserResponse
    fun logIn(request : UserLogInRequest) : UserLogInResponse
    fun logOut()
    fun getAllUserList() : List<UserResponse>?
    fun getAllBoardList() : List<BoardResponse>?
    fun getAllPostList() : List<PostResponse>?
    fun getAllCommentList() : List<CommentResponse>?
    fun getUser(userPrincipal: UserPrincipal, userId: Int) : UserResponse?
    fun resetPassword(userPrincipal: UserPrincipal, userId: Int) : UserResponse
    fun updateUser(userPrincipal: UserPrincipal, userId : Int, request : UserUpdateRequest) : UserResponse
    fun deleteUser(userPrincipal: UserPrincipal, userId : Int, password: String)
}