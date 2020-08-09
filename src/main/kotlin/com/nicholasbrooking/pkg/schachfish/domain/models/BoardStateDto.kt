package com.nicholasbrooking.pkg.schachfish.domain.models

import com.nicholasbrooking.pkg.schachfish.domain.entities.Piece
import java.util.*

data class BoardStateDto (
        val pieceMatrix: Array<Array<Piece?>> = Array(size = 8) { Array<Piece?>(size = 8) { null } },
        val canCastleDto: Map<Colour, CanCastleDto>,
        val enPassantDto: EnPassantDto,
        val turn: Colour
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BoardStateDto

        if (!pieceMatrix.contentDeepEquals(other.pieceMatrix)) return false

        return true
    }

    override fun hashCode(): Int {
        return pieceMatrix.contentDeepHashCode()
    }
}

