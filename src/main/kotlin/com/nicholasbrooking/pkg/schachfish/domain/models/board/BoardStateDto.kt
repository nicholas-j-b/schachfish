package com.nicholasbrooking.pkg.schachfish.domain.models.board

import com.nicholasbrooking.pkg.schachfish.domain.models.Colour
import com.nicholasbrooking.pkg.schachfish.domain.models.move.MoveCollectionDto
import com.nicholasbrooking.pkg.schachfish.domain.models.pieces.PieceDto

data class BoardStateDto (
        val pieceMatrix: Array<Array<PieceDto?>> = Array(size = 8) { Array<PieceDto?>(size = 8) { null } },
        val canCastleDto: Map<Colour, CanCastleDto> = mapOf(),
        val moveCollectionDto: MoveCollectionDto = MoveCollectionDto(emptyList()),
        val turn: Colour = Colour.white
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BoardStateDto

        if (!pieceMatrix.contentDeepEquals(other.pieceMatrix)) return false
        if (canCastleDto != other.canCastleDto) return false
        if (turn != other.turn) return false

        return true
    }

    override fun hashCode(): Int {
        var result = pieceMatrix.contentDeepHashCode()
        result = 31 * result + canCastleDto.hashCode()
        result = 31 * result + turn.hashCode()
        return result
    }
}
