package com.nicholasbrooking.pkg.schachfish.service.mapper

import com.nicholasbrooking.pkg.schachfish.api.models.*
import com.nicholasbrooking.pkg.schachfish.service.exception.SchachfishInvalidState


fun com.nicholasbrooking.pkg.schachfish.domain.models.board.BoardStateDto.toApiDto(): BoardStateDto {
    return BoardStateDto(
            blackStatus = buildColourStatusDto(com.nicholasbrooking.pkg.schachfish.domain.models.Colour.black, this),
            whiteStatus = buildColourStatusDto(com.nicholasbrooking.pkg.schachfish.domain.models.Colour.white, this),
            turn = this.turn.toApiEnum(),
            enPassant = this.enPassantDto.toApiDto()
    )
}

fun com.nicholasbrooking.pkg.schachfish.domain.models.board.EnPassantDto.toApiDto(): EnPassantDto {
    return EnPassantDto(
            possible = this.possible,
            taken = this.taken?.toApiDto()
    )
}

fun com.nicholasbrooking.pkg.schachfish.domain.models.Colour.toApiEnum(): Colour {
    return when (this) {
        com.nicholasbrooking.pkg.schachfish.domain.models.Colour.black -> Colour.black
        com.nicholasbrooking.pkg.schachfish.domain.models.Colour.white -> Colour.white
    }
}

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

fun com.nicholasbrooking.pkg.schachfish.domain.models.pieces.PieceDto.toApiDto(): PieceDto {
    return PieceDto(
            position = this.position.toApiDto(),
            name = this.pieceType.toApiDto()
    )
}

fun buildColourStatusDto(colour: com.nicholasbrooking.pkg.schachfish.domain.models.Colour, boardStateDto: com.nicholasbrooking.pkg.schachfish.domain.models.board.BoardStateDto): ColourStatusDto {
    val canCastleDto = boardStateDto.canCastleDto[colour]
            ?: throw SchachfishInvalidState("castling state incorrectly set")
    val pieces = mutableListOf<PieceDto>()
    boardStateDto.pieceMatrix.forEach {
        it.forEach {pieceDto ->
            if (pieceDto?.colour == colour) {
                pieces.add(pieceDto.toApiDto())
            }
        }
    }
    return ColourStatusDto(
            canCastleKingSide = canCastleDto.kingSide,
            canCastleQueenSide = canCastleDto.queenSide,
            pieces = pieces.toTypedArray()
    )
}