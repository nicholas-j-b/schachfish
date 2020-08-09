package com.nicholasbrooking.pkg.schachfish.domain.models.board

data class EnPassantDto (
    val possible: Boolean = false,
    val taken: PositionDto? = PositionDto()
)

