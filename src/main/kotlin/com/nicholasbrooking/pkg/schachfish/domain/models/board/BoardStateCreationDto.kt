package com.nicholasbrooking.pkg.schachfish.domain.models.board

import com.nicholasbrooking.pkg.schachfish.domain.models.Colour
import com.nicholasbrooking.pkg.schachfish.domain.models.pieces.PieceDto

data class BoardStateCreationDto (
        val pieceList: List<PieceDto>,
        val canCastleDto: Map<Colour, CanCastleDto> = mapOf(),
        val enPassantDto: EnPassantDto = EnPassantDto(),
        val turn: Colour = Colour.white
)
