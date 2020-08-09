package com.nicholasbrooking.pkg.schachfish.domain.models.pieces

import com.nicholasbrooking.pkg.schachfish.domain.models.Colour
import com.nicholasbrooking.pkg.schachfish.domain.models.PieceType
import com.nicholasbrooking.pkg.schachfish.domain.models.board.PositionDto

data class PieceCreationDto(
        val position: PositionDto,
        val colour: Colour,
        val type: PieceType
)
