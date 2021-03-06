package com.nicholasbrooking.pkg.schachfish.domain.models.configuration.pawn

import com.nicholasbrooking.pkg.schachfish.domain.models.move.DirectionDto
import org.springframework.stereotype.Component

@Component
object BlackPawnConfiguration : PawnConfiguration() {
    override val directions: List<DirectionDto> = emptyList()
    override val homeRank = 6
    override val promotionRank = 0
    override val standardDirection = DirectionDto(0, -1)
    override val takeDirections = listOf(
            DirectionDto(-1, -1),
            DirectionDto(1, -1)
    )
    override val openingMovementSize = 2
}