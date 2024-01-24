package com.b2.backoffice.domain.board.service

import com.b2.backoffice.domain.board.dto.BoardCreateRequest
import com.b2.backoffice.domain.board.dto.BoardDeleteRequest
import com.b2.backoffice.domain.board.dto.BoardResponse
import com.b2.backoffice.domain.board.dto.BoardUpdateRequest
import com.b2.backoffice.domain.board.model.BoardEntity
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

    override fun getBoard(boardId: Int): BoardResponse {
        return boardRepository.findByIdOrNull(boardId)
            ?.toResponse()
            ?:throw IllegalArgumentException()
    }

    override fun createBoard(request: BoardCreateRequest): BoardResponse {
        return boardRepository.save(
            BoardEntity(
                    title = request.title,
                    contents = request.contents,
                )
            ).toResponse()
    }

    override fun updateBoard(boardId: Int, request: BoardUpdateRequest): BoardResponse {

        // 비밀번호 검증

        var board = boardRepository.findByIdOrNull(boardId)
            ?:throw IllegalArgumentException()

        board.title = request.title
        board.contents = request.contents

        return boardRepository.save(board).toResponse()
    }

    override fun deleteBoard(boardId: Int, request: BoardDeleteRequest) {

        // 비밀번호 검증

        val Board = boardRepository.findByIdOrNull(boardId)
            ?:throw IllegalArgumentException()

        boardRepository.delete(Board)
    }
}
fun BoardEntity.toResponse() : BoardResponse
{
    return BoardResponse(
        id = id!!,
        createAt = createdAt,
        title = title,
        contents = contents
    )
}