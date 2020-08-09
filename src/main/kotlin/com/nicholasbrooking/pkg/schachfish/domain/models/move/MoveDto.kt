package com.nicholasbrooking.pkg.schachfish.domain.models.move

import com.nicholasbrooking.pkg.schachfish.domain.models.PieceType
import com.nicholasbrooking.pkg.schachfish.domain.models.board.PositionDto

data class MoveDto(
        val to: PositionDto,
        val from: PositionDto,
        val takenPiece: PositionDto?,
        val promoteTo: PieceType?
)
