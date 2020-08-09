package com.nicholasbrooking.pkg.schachfish.domain.models

data class MoveDto(
        val to: PositionDto,
        val from: PositionDto,
        val takenPiece: PositionDto?
){}
