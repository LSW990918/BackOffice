package com.b2.backoffice.domain.board.service

import com.b2.backoffice.domain.board.dto.BoardCreateRequest
import com.b2.backoffice.domain.board.dto.BoardResponse
import com.b2.backoffice.domain.board.dto.BoardUpdateRequest
import com.b2.backoffice.infra.security.UserPrincipal

interface BoardService {
    fun getBoardList() : List<BoardResponse>
    fun getBoard(boardId: Int) : BoardResponse
    fun createBoard(userPrincipal: UserPrincipal, request: BoardCreateRequest) : BoardResponse
    fun updateBoard(boardId: Int, request: BoardUpdateRequest) : BoardResponse
    fun deleteBoard(userPrincipal: UserPrincipal, boardId: Int, password:String)

}