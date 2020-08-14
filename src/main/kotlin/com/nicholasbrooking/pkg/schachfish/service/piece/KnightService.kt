package com.nicholasbrooking.pkg.schachfish.service.piece

import com.nicholasbrooking.pkg.schachfish.domain.models.Colour
import com.nicholasbrooking.pkg.schachfish.domain.models.PieceType
import com.nicholasbrooking.pkg.schachfish.domain.models.board.BoardStateDto
import com.nicholasbrooking.pkg.schachfish.domain.models.board.PositionDto
import com.nicholasbrooking.pkg.schachfish.domain.models.configuration.knight.KnightConfiguration
import com.nicholasbrooking.pkg.schachfish.domain.models.move.MoveDto
import com.nicholasbrooking.pkg.schachfish.domain.models.pieces.PieceDto
import com.nicholasbrooking.pkg.schachfish.service.configuration.PieceConfigurationService
import com.nicholasbrooking.pkg.schachfish.service.exception.SchachfishInvalidState
import com.nicholasbrooking.pkg.schachfish.service.position.PositionService
import org.springframework.stereotype.Service

@Service
class KnightService(
        private val pieceConfigurationService: PieceConfigurationService,
        private val positionService: PositionService

) : PieceService {
    override fun getLegalMoves(pieceDto: PieceDto, boardStateDto: BoardStateDto): List<MoveDto> {
        val config = getConfig(pieceDto.colour)
        val directionDtos = config.directions
        return directionDtos.mapNotNull {
            val newPosition = positionService.getNextPosition(pieceDto.position, it)
            when {
                newPosition == null -> null
                positionService.isSquareOccupiedBy(Colour.getOtherColour(pieceDto.colour), newPosition, boardStateDto) -> {
                    MoveDto(
                            from = pieceDto.position,
                            to = newPosition,
                            takenPiece = newPosition,
                            promoteTo = null
                    )
                }
                positionService.isSquareOccupiedBy(pieceDto.colour, newPosition, boardStateDto) -> null
                positionService.isSquareEmpty(newPosition, boardStateDto) -> {
                    MoveDto(
                            from = pieceDto.position,
                            to = newPosition,
                            takenPiece = null,
                            promoteTo = null
                    )
                }
                else -> throw SchachfishInvalidState("Knight taking non coloured piece")
            }
        }
    }

    override fun move(pieceDto: PieceDto, to: PositionDto) {
        pieceDto.position = to
    }

    private fun getConfig(colour: Colour): KnightConfiguration {
        return pieceConfigurationService.fromColourAndPieceType(colour, PieceType.knight) as KnightConfiguration
    }
}