package com.b2.backoffice.domain.board.service

import com.b2.backoffice.domain.board.dto.BoardCreateRequest
import com.b2.backoffice.domain.board.dto.BoardResponse
import com.b2.backoffice.domain.board.dto.BoardUpdateRequest
import com.b2.backoffice.domain.board.model.BoardEntity
import com.b2.backoffice.domain.board.repository.BoardRepository
import com.b2.backoffice.domain.exception.ModelNotFoundException
import com.b2.backoffice.domain.user.repository.UserRepository
import com.b2.backoffice.infra.security.SecurityService
import com.b2.backoffice.infra.security.UserPrincipal
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BoardServiceImpl (
    private val userRepository: UserRepository,
    private val boardRepository: BoardRepository,
    private val securityService: SecurityService,
): BoardService {
    override fun getBoardList(): List<BoardResponse> {
        return boardRepository.findAll().map{it.toResponse()}
    }

    override fun getBoard(boardId: Int): BoardResponse {
        return boardRepository.findByIdOrNull(boardId)
            ?.toResponse()
            ?:throw ModelNotFoundException("Board", boardId)
    }

    @Transactional
    override fun createBoard(userPrincipal: UserPrincipal, request: BoardCreateRequest): BoardResponse {
        return boardRepository.save(
            BoardEntity(
                    title = request.title,
                    contents = request.contents,
                )
            ).toResponse()
    }

    @Transactional
    override fun updateBoard(boardId: Int, request: BoardUpdateRequest): BoardResponse {
        var board = boardRepository.findByIdOrNull(boardId)
            ?:throw ModelNotFoundException("Board", boardId)

        board.title = request.title
        board.contents = request.contents

        return boardRepository.save(board).toResponse()
    }

    @Transactional
    override fun deleteBoard(userPrincipal: UserPrincipal, boardId: Int, password:String) {
        val user = userRepository.findByIdOrNull(userPrincipal.id)
            ?: throw ModelNotFoundException("User", userPrincipal.id)

        securityService.chkPassword(password, user.password)

        val board = boardRepository.findByIdOrNull(boardId)
            ?:throw ModelNotFoundException("Board", boardId)

        //boardRepository.delete(Board)
        board.isDeleted = true
        boardRepository.save(board)
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