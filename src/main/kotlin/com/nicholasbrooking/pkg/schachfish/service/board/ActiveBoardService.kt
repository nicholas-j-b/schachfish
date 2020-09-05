package com.nicholasbrooking.pkg.schachfish.service.board

import com.nicholasbrooking.pkg.schachfish.domain.models.board.ActiveBoard
import com.nicholasbrooking.pkg.schachfish.domain.models.board.BoardStateDto
import com.nicholasbrooking.pkg.schachfish.repositories.ActiveBoardRepository
import com.nicholasbrooking.pkg.schachfish.service.exception.SchachfishBoardNotFound
import org.springframework.stereotype.Service
import com.google.gson.Gson

@Service
class ActiveBoardService(
        private val activeBoardRepository: ActiveBoardRepository
) {
    private val gson = Gson()

    fun getBoard(id: Long): ActiveBoard = activeBoardRepository.findById(id).orElseThrow {
        SchachfishBoardNotFound("for id $id")
    }

    fun getBoardState(id: Long): BoardStateDto {
        val board = getBoard(id)
        return gson.fromJson(board.currentState, BoardStateDto::class.java)
    }

    fun updateBoardState(id: Long, boardStateDto: BoardStateDto) {
        val board = getBoard(id)
        board.currentState = boardStateDto.serialise()
        activeBoardRepository.save(board)
    }

    fun createBoard(boardStateDto: BoardStateDto): Long {
        val id = ActiveBoardIdUtil.getNextId()
        val activeBoard = ActiveBoard(id, boardStateDto.serialise())
        activeBoardRepository.save(activeBoard)
        return id
    }

    private fun BoardStateDto.serialise() = gson.toJson(this)
}
