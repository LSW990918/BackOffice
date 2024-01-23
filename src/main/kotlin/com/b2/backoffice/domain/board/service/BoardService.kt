package com.b2.backoffice.domain.board.service

import com.b2.backoffice.domain.board.dto.BoardCreateRequest
import com.b2.backoffice.domain.board.dto.BoardDeleteRequest
import com.b2.backoffice.domain.board.dto.BoardResponse
import com.b2.backoffice.domain.board.dto.BoardUpdateRequest

interface BoardService {
    fun getBoardList() : List<BoardResponse>
    fun getBoard(boardId: Int) : BoardResponse
    fun createBoard(request: BoardCreateRequest) : BoardResponse
    fun updateBoard(boardId: Int, request: BoardUpdateRequest) : BoardResponse
    fun deleteBoard(boardId : Int, request: BoardDeleteRequest)

}