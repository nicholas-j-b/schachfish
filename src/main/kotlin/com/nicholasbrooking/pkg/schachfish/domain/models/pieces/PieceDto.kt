package com.nicholasbrooking.pkg.schachfish.domain.models.pieces

import com.nicholasbrooking.pkg.schachfish.domain.models.*
import com.nicholasbrooking.pkg.schachfish.domain.models.board.PositionDto

interface PieceDto {
    val colour: Colour
    val id: Int
    val pieceType: PieceType
    val pieceName: String
    var position: PositionDto
}
