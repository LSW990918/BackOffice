package com.b2.backoffice.domain.board.repository

import com.b2.backoffice.domain.board.model.BoardEntity
import org.springframework.data.jpa.repository.JpaRepository

interface BoardRepository : JpaRepository<BoardEntity, Long>{

}
