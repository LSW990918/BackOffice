package com.b2.backoffice.domain.follow.service

import com.b2.backoffice.domain.board.dto.BoardResponse
import com.b2.backoffice.domain.follow.dto.FollowResponse

interface FollowService {
    //인증/인가 완료시 userId 받아오기
    fun addFollow(userId: Int, boardId: Int)
    fun cancleFollow(userId: Int, boardId: Int)
    fun getFollowList(userId: Int): List<BoardResponse>

}