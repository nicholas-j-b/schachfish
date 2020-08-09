package com.nicholasbrooking.pkg.schachfish.service.piece

import com.nicholasbrooking.pkg.schachfish.domain.models.Colour
import com.nicholasbrooking.pkg.schachfish.domain.models.PieceType
import com.nicholasbrooking.pkg.schachfish.domain.models.board.BoardStateDto
import com.nicholasbrooking.pkg.schachfish.domain.models.configuration.PieceConfiguration
import com.nicholasbrooking.pkg.schachfish.domain.models.configuration.bishop.BishopConfiguration
import com.nicholasbrooking.pkg.schachfish.domain.models.configuration.rook.RookConfiguration
import com.nicholasbrooking.pkg.schachfish.domain.models.move.MoveDto
import com.nicholasbrooking.pkg.schachfish.domain.models.pieces.PieceDto
import com.nicholasbrooking.pkg.schachfish.service.configuration.PieceConfigurationService
import com.nicholasbrooking.pkg.schachfish.service.position.PositionService
import org.springframework.stereotype.Service

@Service
class SlidePieceService (
        private val positionService: PositionService
) {
    fun getSlideMoves(pieceDto: PieceDto, boardStateDto: BoardStateDto, config: PieceConfiguration, limit: Int? = null): List<MoveDto> {
        val directions = config.directions
        return directions.flatMap {
            positionService.getEmptyPositionRay(
                    startingPositionDto = pieceDto.position,
                    directionDto =  it,
                    boardStateDto = boardStateDto,
                    takeColour = Colour.getOtherColour(pieceDto.colour),
                    limit = limit
            ).map {
                MoveDto(
                        from = pieceDto.position,
                        to = it,
                        takenPiece = null,
                        promoteTo = null
                )
            }
        }
    }
}
