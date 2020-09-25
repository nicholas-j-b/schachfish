package com.nicholasbrooking.pkg.schachfish.service.board

import com.nicholasbrooking.pkg.schachfish.domain.models.board.ActiveBoard
import com.nicholasbrooking.pkg.schachfish.domain.models.board.BoardStateDto
import com.nicholasbrooking.pkg.schachfish.repositories.ActiveBoardRepository
import com.nicholasbrooking.pkg.schachfish.service.exception.SchachfishBoardNotFound
import org.springframework.stereotype.Service
import com.google.gson.Gson
import com.nicholasbrooking.pkg.schachfish.service.exception.SchachfishBoardIdClash
import org.springframework.data.mapping.model.MappingInstantiationException
import org.springframework.transaction.annotation.Transactional

@Service
class ActiveBoardService(
        private val activeBoardRepository: ActiveBoardRepository
) {
    private val gson = Gson()

    @Transactional
    fun getBoard(id: Long): ActiveBoard = activeBoardRepository.findById(id).orElseThrow {
        SchachfishBoardNotFound("for id $id")
    }

    @Transactional
    fun getBoardState(id: Long): BoardStateDto {
        val board = getBoard(id)
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
        try {
            if (!activeBoardRepository.existsById(boardId)) {
                throw SchachfishBoardNotFound("Board not found")
            }
        } catch (e: MappingInstantiationException) { // TODO("why is this throwing?")
            throw SchachfishBoardNotFound("Board not found")
        }
    }

    private fun checkBoardIdDoesNotExist(boardId: Long) {
        try {
            if (activeBoardRepository.existsById(boardId)) {
                throw SchachfishBoardIdClash("Board already exists")
            }
        } catch (e: MappingInstantiationException) { // TODO("why is this throwing?")
            throw SchachfishBoardNotFound("Board not found")
        }
    }

    private fun BoardStateDto.serialise() = gson.toJson(this)
}
