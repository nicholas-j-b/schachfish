package com.nicholasbrooking.pkg.schachfish.domain.models.move

import com.nicholasbrooking.pkg.schachfish.domain.models.PieceType
import com.nicholasbrooking.pkg.schachfish.domain.models.board.PositionDto

data class MoveDto(
        val from: PositionDto,
        val to: PositionDto,
        val takenPiece: PositionDto?,
        val promoteTo: PieceType?
)
