package com.nicholasbrooking.pkg.schachfish.domain.models.configuration.king

import com.nicholasbrooking.pkg.schachfish.domain.models.configuration.PieceConfiguration
import com.nicholasbrooking.pkg.schachfish.domain.models.move.DirectionDto
import org.springframework.context.annotation.Configuration

@Configuration
abstract class KingConfiguration : PieceConfiguration {
    override val directions: List<DirectionDto> = listOf(
            DirectionDto(0, 1),
            DirectionDto(0, -1),
            DirectionDto(1, 0),
            DirectionDto(-1, 0),

            DirectionDto(1, 1),
            DirectionDto(1, -1),
            DirectionDto(-1, 1),
            DirectionDto(-1, -1)
    )
}
