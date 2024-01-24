package com.b2.backoffice.domain.board.controller

import com.b2.backoffice.domain.board.dto.BoardCreateRequest
import com.b2.backoffice.domain.board.dto.BoardDeleteRequest
import com.b2.backoffice.domain.board.dto.BoardResponse
import com.b2.backoffice.domain.board.dto.BoardUpdateRequest
import com.b2.backoffice.domain.board.service.BoardService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/boards")
class BoardController(
    private var boardService: BoardService
) {
    @GetMapping()
    fun getBoardList() : ResponseEntity<List<BoardResponse>>{
        return ResponseEntity.status(HttpStatus.OK).body(boardService.getBoardList())
    }

    @GetMapping("/{boardId}")
    fun getBoard( @PathVariable boardId : Int )
                  : ResponseEntity<BoardResponse>{

        return ResponseEntity.status(HttpStatus.OK).body(boardService.getBoard(boardId))
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    fun createBoard(
        @RequestBody request : BoardCreateRequest
    ) : ResponseEntity<BoardResponse>{

        return ResponseEntity.status(HttpStatus.CREATED).body(boardService.createBoard(request))
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @PutMapping("/{boardId}/update")
    fun updateBoard(
        @PathVariable boardId: Int,
        @RequestBody request : BoardUpdateRequest
    ) : ResponseEntity<BoardResponse>{

        return ResponseEntity.status(HttpStatus.OK).body(boardService.updateBoard(boardId, request))
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{boardId}/delete")
    fun deleteBoard(
        @PathVariable boardId : Int,
        @RequestBody request : BoardDeleteRequest
    ) : ResponseEntity<Unit> {

        boardService.deleteBoard(boardId, request)

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}