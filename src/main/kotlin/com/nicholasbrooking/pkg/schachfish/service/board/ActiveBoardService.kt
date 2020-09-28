package com.nicholasbrooking.pkg.schachfish.service.board

import com.nicholasbrooking.pkg.schachfish.domain.models.board.ActiveBoard
import com.nicholasbrooking.pkg.schachfish.domain.models.board.BoardStateDto
import com.nicholasbrooking.pkg.schachfish.repositories.ActiveBoardRepository
import com.nicholasbrooking.pkg.schachfish.service.exception.SchachfishBoardNotFound
import org.springframework.stereotype.Service
import com.google.gson.Gson
import com.nicholasbrooking.pkg.schachfish.repositories.exists
import com.nicholasbrooking.pkg.schachfish.service.exception.SchachfishBoardIdClash
import org.springframework.data.mapping.model.MappingInstantiationException
import org.springframework.transaction.annotation.Transactional

@Service
class ActiveBoardService(
        private val activeBoardRepository: ActiveBoardRepository
) {
    private val gson = Gson()

    @Transactional
    fun getBoard(boardId: Long): ActiveBoard {
        checkBoardExists(boardId)
        return activeBoardRepository.findById(boardId).orElseThrow {
            SchachfishBoardNotFound("for id $boardId")
        }
    }

    @Transactional
    fun getBoardState(boardId: Long): BoardStateDto {
        val board = getBoard(boardId)
        return gson.fromJson(board.currentState, BoardStateDto::class.java)
    }

    @Transactional
    fun updateBoardState(boardId: Long, boardStateDto: BoardStateDto) {
        checkBoardExists(boardId)
        val board = getBoard(boardId)
        board.currentState = boardStateDto.serialise()
        activeBoardRepository.save(board)
    }

    @Transactional
    fun createBoard(boardStateDto: BoardStateDto): Long {
        val boardId = ActiveBoardIdUtil.getNextId()
        checkBoardIdDoesNotExist(boardId)
        val activeBoard = ActiveBoard(boardId, boardStateDto.serialise())
        activeBoardRepository.save(activeBoard)
        return boardId
    }

    @Transactional
    fun deleteBoard(boardId: Long) {
        checkBoardExists(boardId)
        activeBoardRepository.deleteById(boardId)
    }

    private fun checkBoardExists(boardId: Long) {
        if (!activeBoardRepository.exists(boardId)) {
            throw SchachfishBoardNotFound("Board not found")
        }
    }

    private fun checkBoardIdDoesNotExist(boardId: Long) {
        if (activeBoardRepository.exists(boardId)) {
            throw SchachfishBoardIdClash("Board already exists")
        }
    }

    private fun BoardStateDto.serialise() = gson.toJson(this)
}
