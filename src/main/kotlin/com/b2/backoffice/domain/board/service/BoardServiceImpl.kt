package com.b2.backoffice.domain.board.service

import com.b2.backoffice.domain.board.dto.BoardCreateRequest
import com.b2.backoffice.domain.board.dto.BoardDeleteRequest
import com.b2.backoffice.domain.board.dto.BoardResponse
import com.b2.backoffice.domain.board.dto.BoardUpdateRequest
import org.springframework.stereotype.Service

@Service
class BoardServiceImpl : BoardService {
    override fun getBoardList(): List<BoardResponse> {
        TODO("Not yet implemented")
    }

    override fun getBoard(boardId: Long): BoardResponse {
        TODO("Not yet implemented")
    }

    override fun createBoard(request: BoardCreateRequest): BoardResponse {
        TODO("Not yet implemented")
    }

    override fun updateBoard(boardId: Long, request: BoardUpdateRequest): BoardResponse {
        TODO("Not yet implemented")
    }

    override fun deleteBoard(boardId: Long, request: BoardDeleteRequest) {
        TODO("Not yet implemented")
    }
}