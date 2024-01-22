package com.b2.backoffice.domain.board.service

import com.b2.backoffice.domain.board.dto.BoardCreateRequest
import com.b2.backoffice.domain.board.dto.BoardDeleteRequest
import com.b2.backoffice.domain.board.dto.BoardResponse
import com.b2.backoffice.domain.board.dto.BoardUpdateRequest
import com.b2.backoffice.domain.board.model.Board
import com.b2.backoffice.domain.board.model.toResponse
import com.b2.backoffice.domain.board.repository.BoardRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class BoardServiceImpl (
    private val boardRepository: BoardRepository
): BoardService {
    override fun getBoardList(): List<BoardResponse> {

        // findAllByUserId

        TODO()
    }

    override fun getBoard(boardId: Long): BoardResponse {
        return boardRepository.findByIdOrNull(boardId)
            ?.toResponse()
            ?:throw IllegalArgumentException()
    }

    override fun createBoard(request: BoardCreateRequest): BoardResponse {
        return boardRepository.save(
            Board(
                    title = request.title,
                    contents = request.contents,
                )
            ).toResponse()
    }

    override fun updateBoard(boardId: Long, request: BoardUpdateRequest): BoardResponse {

        // 비밀번호 검증

        var Board = boardRepository.findByIdOrNull(boardId)
            ?:throw IllegalArgumentException()

        Board.title = request.title
        Board.contents = request.contents

        return boardRepository.save(Board).toResponse()
    }

    override fun deleteBoard(boardId: Long, request: BoardDeleteRequest) {

        // 비밀번호 검증

        val Board = boardRepository.findByIdOrNull(boardId)
            ?:throw IllegalArgumentException()

        boardRepository.delete(Board)
    }
}