package com.nicholasbrooking.pkg.schachfish.domain.models.configuration

import com.nicholasbrooking.pkg.schachfish.domain.models.move.DirectionDto
import org.springframework.context.annotation.Configuration

@Configuration
interface PieceConfiguration {
    val directions: List<DirectionDto>
}
