package com.nicholasbrooking.pkg.schachfish.domain.models.board

import com.nicholasbrooking.pkg.schachfish.domain.models.Colour
import com.nicholasbrooking.pkg.schachfish.domain.models.move.MoveCollectionDto
import com.nicholasbrooking.pkg.schachfish.domain.models.pieces.PieceDto

data class BoardStateCreationDto (
        val pieceList: List<PieceDto>,
        val canCastleDto: Map<Colour, CanCastleDto> = mapOf(),
        val moveCollectionDto: MoveCollectionDto = MoveCollectionDto(emptyList()),
        val turn: Colour = Colour.white
)
