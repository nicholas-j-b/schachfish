package com.nicholasbrooking.pkg.schachfish.domain.util

import com.nicholasbrooking.pkg.schachfish.domain.models.pieces.*
import com.nicholasbrooking.pkg.schachfish.domain.models.*
import java.lang.Exception

class PieceDtoBuilder {
    companion object {
        fun fromPieceCreationDto(pieceCreationDto: PieceCreationDto): PieceDto {
            return PieceDto(
                    pieceCreationDto.colour,
                    0,
                    pieceCreationDto.type,
                    pieceCreationDto.type.toString(),
                    pieceCreationDto.position,
                    false
            )
        }
    }
}
