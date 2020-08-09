package com.nicholasbrooking.pkg.schachfish.domain.models

data class PieceDto(
        val position: PositionDto,
        val colour: Colour,
        val type: PieceType
)
