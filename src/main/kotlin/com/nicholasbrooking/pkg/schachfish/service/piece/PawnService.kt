package com.nicholasbrooking.pkg.schachfish.service.piece

import com.nicholasbrooking.pkg.schachfish.domain.models.Colour
import com.nicholasbrooking.pkg.schachfish.domain.models.PieceType
import com.nicholasbrooking.pkg.schachfish.domain.models.board.BoardStateDto
import com.nicholasbrooking.pkg.schachfish.domain.models.board.PositionDto
import com.nicholasbrooking.pkg.schachfish.domain.models.configuration.pawn.PawnConfiguration
import com.nicholasbrooking.pkg.schachfish.domain.models.move.MoveDto
import com.nicholasbrooking.pkg.schachfish.domain.models.pieces.PieceDto
import com.nicholasbrooking.pkg.schachfish.service.configuration.PieceConfigurationService
import com.nicholasbrooking.pkg.schachfish.service.position.PositionService
import org.springframework.stereotype.Service

@Service
class PawnService (
        private val pieceConfigurationService: PieceConfigurationService,
        private val positionService: PositionService
) : PieceService {
    override fun getLegalMoves(pieceDto: PieceDto, boardStateDto: BoardStateDto): List<MoveDto> {
        val forwardMoves = getOpeningMoves(pieceDto, boardStateDto)
        val takeMoves = getTakeMoves(pieceDto, boardStateDto)
        return addPromotionMoves(pieceDto, listOf(forwardMoves, takeMoves).flatten())
    }

    override fun move(pieceDto: PieceDto, to: PositionDto) {
        pieceDto.position = to
    }

    fun addPromotionMoves(pieceDto: PieceDto, moves: List<MoveDto>): List<MoveDto> {
        val config = getConfig(pieceDto.colour)
        val promotionRank = config.promotionRank
        return moves.flatMap { moveDto ->
            if (moveDto.to.y == promotionRank) {
                config.promotionPieces.map {
                    MoveDto(
                            from = moveDto.from,
                            to = moveDto.to,
                            takenPiece = moveDto.takenPiece,
                            promoteTo = it
                    )
                }
            } else {
                listOf(moveDto)
            }
        }
    }

    private fun isOnHomeSquare(pieceDto: PieceDto): Boolean {
        val config = getConfig(pieceDto.colour)
        return pieceDto.position.y == config.homeRank
    }

    private fun getStandardMoves(pieceDto: PieceDto, boardStateDto: BoardStateDto): List<MoveDto> {
        val config = getConfig(pieceDto.colour)
        val directionDto = config.standardDirection
        val newPositionDto = positionService.getNextPosition(pieceDto.position, directionDto)
        return if (newPositionDto != null && positionService.isSquareEmpty(newPositionDto, boardStateDto)) {
            listOf(MoveDto(
                    from = pieceDto.position,
                    to = newPositionDto,
                    takenPiece = null,
                    promoteTo = null
            ))
        } else {
            emptyList()
        }
    }

    private fun getOpeningMoves(pieceDto: PieceDto, boardStateDto: BoardStateDto): List<MoveDto> {
        if (!isOnHomeSquare(pieceDto)) {
            return getStandardMoves(pieceDto, boardStateDto)
        }
        val config = getConfig(pieceDto.colour)
        val directionDto = config.standardDirection
        val numberOfSquaresToMove = config.openingMovementSize
        return positionService.getEmptyPositionRay(pieceDto.position, directionDto,
                boardStateDto, null, numberOfSquaresToMove).map {
            MoveDto(
                    from = pieceDto.position,
                    to = it,
                    takenPiece = null,
                    promoteTo = null
            )
        }
    }

    private fun getTakeMoves(pieceDto: PieceDto, boardStateDto: BoardStateDto): List<MoveDto> {
        val config = getConfig(pieceDto.colour)
        val directionDtos = config.takeDirections
        val moves = mutableListOf<MoveDto>()
        directionDtos.forEach { direction ->
            val newPosition = positionService.getNextPosition(pieceDto.position, direction)
            if (newPosition != null) {
                if (positionService.isSquareOccupiedBy(Colour.getOtherColour(pieceDto.colour), newPosition, boardStateDto)) {
                    moves.add(MoveDto(
                            from = pieceDto.position,
                            to = newPosition,
                            takenPiece = newPosition,
                            promoteTo = null
                    ))
                }
            }
        }
        return moves
    }

    //Todo("en passant")

    private fun getConfig(colour: Colour): PawnConfiguration {
        return pieceConfigurationService.fromColourAndPieceType(colour, PieceType.pawn) as PawnConfiguration
    }
}