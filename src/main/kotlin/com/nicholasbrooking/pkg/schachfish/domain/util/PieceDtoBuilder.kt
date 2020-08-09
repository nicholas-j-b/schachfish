package com.nicholasbrooking.pkg.schachfish.domain.util

import com.nicholasbrooking.pkg.schachfish.domain.models.pieces.*
import com.nicholasbrooking.pkg.schachfish.domain.models.*
import java.lang.Exception

class PieceDtoBuilder {
    companion object {
        fun fromPieceCreationDto(pieceCreationDto: PieceCreationDto): PieceDto {
            return when(pieceCreationDto.type) {
                PieceType.pawn -> PawnDto(pieceCreationDto.colour, 0, pieceCreationDto.position) //TODO("id")
                PieceType.king -> KingDto(pieceCreationDto.colour, 0, pieceCreationDto.position) //TODO("id")
                PieceType.rook -> RookDto(pieceCreationDto.colour, 0, pieceCreationDto.position) //TODO("id")
                PieceType.bishop -> BishopDto(pieceCreationDto.colour, 0, pieceCreationDto.position) //TODO("id")
                PieceType.queen -> QueenDto(pieceCreationDto.colour, 0, pieceCreationDto.position) //TODO("id")
                else -> throw Exception("bad pieceType: $pieceCreationDto.type")
            }
        }
    }
}
