package com.nicholasbrooking.pkg.schachfish.service.piece

import com.nicholasbrooking.pkg.schachfish.domain.models.Colour
import com.nicholasbrooking.pkg.schachfish.domain.models.PieceType
import com.nicholasbrooking.pkg.schachfish.domain.models.board.BoardStateDto
import com.nicholasbrooking.pkg.schachfish.domain.models.configuration.bishop.BishopConfiguration
import com.nicholasbrooking.pkg.schachfish.domain.models.configuration.queen.QueenConfiguration
import com.nicholasbrooking.pkg.schachfish.domain.models.configuration.rook.RookConfiguration
import com.nicholasbrooking.pkg.schachfish.domain.models.move.MoveDto
import com.nicholasbrooking.pkg.schachfish.domain.models.pieces.PieceDto
import com.nicholasbrooking.pkg.schachfish.service.configuration.PieceConfigurationService
import com.nicholasbrooking.pkg.schachfish.service.position.PositionService
import org.springframework.stereotype.Service

@Service
class QueenService (
        private val pieceConfigurationService: PieceConfigurationService,
        private val slidePieceService: SlidePieceService
) : PieceService {
    override fun getLegalMoves(pieceDto: PieceDto, boardStateDto: BoardStateDto): List<MoveDto> {
        return slidePieceService.getSlideMoves(pieceDto, boardStateDto, getConfig(pieceDto.colour))
    }

    private fun getConfig(colour: Colour): QueenConfiguration {
        return pieceConfigurationService.fromColourAndPieceType(colour, PieceType.queen) as QueenConfiguration
    }
}
