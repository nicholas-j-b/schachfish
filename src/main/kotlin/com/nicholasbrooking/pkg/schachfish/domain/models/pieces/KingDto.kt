package com.nicholasbrooking.pkg.schachfish.domain.models.pieces

import com.nicholasbrooking.pkg.schachfish.domain.models.*
import com.nicholasbrooking.pkg.schachfish.domain.models.board.PositionDto


data class KingDto(
        override val colour: Colour,
        override val id: Int,
        override var position: PositionDto
) : PieceDto {
    override val pieceType = PieceType.king
    override val pieceName = pieceType.toString()
}
