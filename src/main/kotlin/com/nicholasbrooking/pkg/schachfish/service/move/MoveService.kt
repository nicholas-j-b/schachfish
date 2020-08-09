package com.nicholasbrooking.pkg.schachfish.service.move

import com.nicholasbrooking.pkg.schachfish.domain.models.board.BoardStateDto
import com.nicholasbrooking.pkg.schachfish.domain.models.*
import com.nicholasbrooking.pkg.schachfish.domain.models.move.MoveCollectionDto
import com.nicholasbrooking.pkg.schachfish.domain.models.move.MoveDto
import com.nicholasbrooking.pkg.schachfish.domain.models.pieces.PieceDto
import com.nicholasbrooking.pkg.schachfish.service.piece.PawnService
import com.nicholasbrooking.pkg.schachfish.service.piece.RookService
import org.springframework.stereotype.Service

@Service
class MoveService(
        private val pawnService: PawnService,
        private val rookService: RookService
) {
    fun getLegalMoves(boardStateDto: BoardStateDto): MoveCollectionDto {
        return MoveCollectionDto(
                moves = boardStateDto.pieceMatrix.flatMap { pieceArray ->
                    pieceArray.mapNotNull { pieceDto ->
                        if (pieceDto != null) {
                            getLegalMovesForPiece(pieceDto, boardStateDto)
                        } else {
                            null
                        }
                    }.flatten()
                }
        )
    }

    private fun getLegalMovesForPiece(pieceDto: PieceDto, boardStateDto: BoardStateDto): List<MoveDto> {
        val service = when (pieceDto.pieceType) {
            PieceType.pawn -> pawnService
            PieceType.rook -> rookService
            else -> throw Exception()
        }
        return service.getLegalMoves(pieceDto, boardStateDto)
    }
}