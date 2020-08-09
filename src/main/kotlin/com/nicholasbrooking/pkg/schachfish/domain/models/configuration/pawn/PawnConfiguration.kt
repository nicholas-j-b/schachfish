package com.nicholasbrooking.pkg.schachfish.domain.models.configuration.pawn

import com.nicholasbrooking.pkg.schachfish.domain.models.PieceType
import com.nicholasbrooking.pkg.schachfish.domain.models.configuration.PieceConfiguration
import com.nicholasbrooking.pkg.schachfish.domain.models.move.DirectionDto
import org.springframework.context.annotation.Configuration

@Configuration
abstract class PawnConfiguration : PieceConfiguration {
    abstract override val directions: List<DirectionDto>

    abstract val homeRank: Int
    abstract val promotionRank: Int

    abstract val standardDirection: DirectionDto
    abstract val openingMovementSize: Int
    abstract val takeDirections: List<DirectionDto>

    val promotionPieces = listOf(
            PieceType.queen,
            PieceType.rook,
            PieceType.bishop,
            PieceType.knight
    )

}
