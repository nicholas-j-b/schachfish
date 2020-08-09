package com.nicholasbrooking.pkg.schachfish.domain.models.configuration.bishop

import com.nicholasbrooking.pkg.schachfish.domain.models.configuration.PieceConfiguration
import com.nicholasbrooking.pkg.schachfish.domain.models.move.DirectionDto
import org.springframework.context.annotation.Configuration

@Configuration
abstract class BishopConfiguration : PieceConfiguration {
    override val directions: List<DirectionDto> = listOf(
            DirectionDto(1, 1),
            DirectionDto(1, -1),
            DirectionDto(-1, 1),
            DirectionDto(-1, -1)
    )
}
