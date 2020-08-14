package com.nicholasbrooking.pkg.schachfish.service.move

import com.nicholasbrooking.pkg.schachfish.domain.models.board.BoardStateDto
import com.nicholasbrooking.pkg.schachfish.domain.models.*
import com.nicholasbrooking.pkg.schachfish.domain.models.move.MoveCollectionDto
import com.nicholasbrooking.pkg.schachfish.domain.models.move.MoveDto
import com.nicholasbrooking.pkg.schachfish.domain.models.pieces.PieceDto
import com.nicholasbrooking.pkg.schachfish.service.piece.PawnService
import com.nicholasbrooking.pkg.schachfish.service.piece.PieceSwitchService
import com.nicholasbrooking.pkg.schachfish.service.piece.RookService
import org.springframework.stereotype.Service

@Service
class MoveFinderService (
        private val pieceSwitchService: PieceSwitchService
) {
    fun getLegalMoves(boardStateDto: BoardStateDto): MoveCollectionDto {
        val allMoves = getAllMoves(boardStateDto)
        val filteredForLegalMoves = allMoves.filterForLegalMoves(boardStateDto)
        return MoveCollectionDto(moves = filteredForLegalMoves)
    }

    private fun getAllMoves(boardStateDto: BoardStateDto): List<MoveDto> {
        return boardStateDto.pieceMatrix.flatMap { pieceArray ->
            pieceArray.mapNotNull { pieceDto ->
                if (pieceDto != null) {
                    getLegalMovesForPiece(pieceDto, boardStateDto)
                } else {
                    null
                }
            }.flatten()
        }
    }

    private fun List<MoveDto>.filterForLegalMoves(boardStateDto: BoardStateDto): List<MoveDto> {
        return this.filter {
            doesMoveCreateLegalBoardState(it, boardStateDto)
        }
    }

    private fun doesMoveCreateLegalBoardState(moveDto: MoveDto, boardStateDto: BoardStateDto): Boolean {
        return true //TODO
    }


    private fun getLegalMovesForPiece(pieceDto: PieceDto, boardStateDto: BoardStateDto): List<MoveDto> {
        val service = pieceSwitchService.getService(pieceDto.pieceType)
        return service.getLegalMoves(pieceDto, boardStateDto)
    }
}