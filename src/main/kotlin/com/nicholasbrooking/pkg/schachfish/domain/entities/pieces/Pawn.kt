package com.nicholasbrooking.pkg.schachfish.domain.entities.pieces

import com.nicholasbrooking.pkg.schachfish.domain.entities.Piece
import com.nicholasbrooking.pkg.schachfish.domain.models.Colour
import com.nicholasbrooking.pkg.schachfish.domain.models.PositionDto


class Pawn(
        override val colour: Colour,
        override val id: Int,
        override var position: PositionDto
) : Piece() {
    override val pieceName: String = "pawn"
}
