package com.b2.backoffice.domain.follow.service

import com.b2.backoffice.domain.board.dto.BoardResponse

interface FollowService {
    //인증/인가 완료시 userId 받아오기
    fun follow(userId: Int, boardId: Int)
    fun unfollow(userId: Int, boardId: Int)
    fun getFollowList(userId: Int): List<BoardResponse>

}