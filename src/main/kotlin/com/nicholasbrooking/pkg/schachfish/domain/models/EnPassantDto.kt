package com.nicholasbrooking.pkg.schachfish.domain.models

data class EnPassantDto (
    val possible: Boolean,
    val taken: PositionDto?
)

