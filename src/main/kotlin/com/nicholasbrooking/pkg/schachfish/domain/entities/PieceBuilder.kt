package com.nicholasbrooking.pkg.schachfish.domain.entities

import com.nicholasbrooking.pkg.schachfish.domain.entities.pieces.*
import com.nicholasbrooking.pkg.schachfish.domain.models.*
import java.lang.Exception

class PieceBuilder {
    companion object {
        fun fromPieceDto(pieceDto: PieceDto): Piece {
            return when(pieceDto.type) {
                PieceType.pawn -> Pawn(pieceDto.colour, 0, pieceDto.position) //TODO("id")
                PieceType.king -> King(pieceDto.colour, 0, pieceDto.position) //TODO("id")
                else -> throw Exception("bad pieceType: $pieceDto.type")
            }
        }
    }
}
