package com.nicholasbrooking.pkg.schachfish.domain.models.configuration.knight

import com.nicholasbrooking.pkg.schachfish.domain.models.configuration.PieceConfiguration
import com.nicholasbrooking.pkg.schachfish.domain.models.move.DirectionDto
import org.springframework.context.annotation.Configuration

@Configuration
abstract class KnightConfiguration : PieceConfiguration {
    override val directions: List<DirectionDto> = listOf(
            DirectionDto(2, 1),
            DirectionDto(1, 2),
            DirectionDto(2, -1),
            DirectionDto(1, -2),
            DirectionDto(-2, 1),
            DirectionDto(-1, 2),
            DirectionDto(-2, -1),
            DirectionDto(-1, -2)
    )
}
