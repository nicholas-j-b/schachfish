package com.nicholasbrooking.pkg.schachfish.domain.models.configuration.rook

import com.nicholasbrooking.pkg.schachfish.domain.models.Colour
import com.nicholasbrooking.pkg.schachfish.domain.models.PieceType
import com.nicholasbrooking.pkg.schachfish.domain.models.configuration.PieceConfiguration
import com.nicholasbrooking.pkg.schachfish.domain.models.move.DirectionDto
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

@Configuration
abstract class RookConfiguration : PieceConfiguration {
    override val directions: List<DirectionDto> = listOf(
            DirectionDto(0, 1),
            DirectionDto(0, -1),
            DirectionDto(1, 0),
            DirectionDto(-1, 0)
    )
}
