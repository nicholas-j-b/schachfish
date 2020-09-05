package com.nicholasbrooking.pkg.schachfish.service.board

import com.nicholasbrooking.pkg.schachfish.domain.models.board.ActiveBoard
import com.nicholasbrooking.pkg.schachfish.domain.models.board.BoardStateDto
import com.nicholasbrooking.pkg.schachfish.repositories.ActiveBoardRepository
import com.nicholasbrooking.pkg.schachfish.service.exception.SchachfishBoardNotFound
import org.springframework.stereotype.Service

@Service
class ActiveBoardService(
        private val activeBoardRepository: ActiveBoardRepository
) {
    fun getBoard(id: Long): ActiveBoard = activeBoardRepository.findById(id).orElseThrow {
        SchachfishBoardNotFound("for id $id")
    }

    fun createBoard(boardStateDto: BoardStateDto): Long {
        val id = ActiveBoardIdUtil.getNextId()
        activeBoardRepository.save(ActiveBoard(id, boardStateDto))
        return id
    }
}