package com.nicholasbrooking.pkg.schachfish.service.move

import com.nicholasbrooking.pkg.schachfish.domain.models.board.BoardStateDto
import com.nicholasbrooking.pkg.schachfish.domain.models.move.MoveDto
import com.nicholasbrooking.pkg.schachfish.service.board.BoardService
import com.nicholasbrooking.pkg.schachfish.service.exception.SchachfishInvalidMove
import com.nicholasbrooking.pkg.schachfish.service.exception.SchachfishInvalidState
import com.nicholasbrooking.pkg.schachfish.service.piece.PieceSwitchService
import org.springframework.stereotype.Service

@Service
class MoveService (
        private val boardService: BoardService,
        private val pieceSwitchService: PieceSwitchService
) {
    fun move(moveDto: MoveDto, boardStateDto: BoardStateDto): BoardStateDto? {
        try {
            if (moveDto.takenPiece != null) {
                boardService.removePiece(moveDto.takenPiece, boardStateDto)
            }
            val pieceToMove = boardService.getPiece(moveDto.from, boardStateDto)
                    ?: throw SchachfishInvalidMove("no piece in from square found")
            boardService.removePiece(moveDto.from, boardStateDto)
            val service = pieceSwitchService.getService(pieceToMove.pieceType)
            service.move(pieceToMove, moveDto.to)
            pieceToMove.position = moveDto.to
            boardService.addPiecesToBoard(listOf(pieceToMove), boardStateDto)
            return boardStateDto
        } catch (e: Exception) {
            if (e is SchachfishInvalidState || e is SchachfishInvalidMove) {
                return null
            }
        }
        return null
    }

}