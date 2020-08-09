package com.nicholasbrooking.pkg.schachfish.service.mapper

import com.nicholasbrooking.pkg.schachfish.api.models.MoveCollectionDto
import com.nicholasbrooking.pkg.schachfish.api.models.MoveDto
import com.nicholasbrooking.pkg.schachfish.api.models.PieceName
import com.nicholasbrooking.pkg.schachfish.api.models.PositionDto


fun com.nicholasbrooking.pkg.schachfish.domain.models.move.MoveCollectionDto.toApiDto(): MoveCollectionDto {
    return MoveCollectionDto(
            moves = this.moves.map {
                it.toApiDto()
            }.toTypedArray())
}

fun com.nicholasbrooking.pkg.schachfish.domain.models.move.MoveDto.toApiDto(): MoveDto {
    return MoveDto(
            from = this.from.toApiDto(),
            to = this.to.toApiDto(),
            takenPiece = this.takenPiece?.toApiDto(),
            promoteTo = this.promoteTo?.toApiDto()
    )
}

fun com.nicholasbrooking.pkg.schachfish.domain.models.PieceType.toApiDto(): PieceName {
    return PieceName.values().first {
        it.toInternalEnum() == this
    }
}

fun com.nicholasbrooking.pkg.schachfish.domain.models.board.PositionDto.toApiDto(): PositionDto {
    return PositionDto(
            x = this.x,
            y = this.y
    )
}
