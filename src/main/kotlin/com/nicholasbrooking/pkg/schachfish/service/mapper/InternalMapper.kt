package com.nicholasbrooking.pkg.schachfish.service.mapper

import com.nicholasbrooking.pkg.schachfish.domain.util.PieceDtoBuilder
import com.nicholasbrooking.pkg.schachfish.domain.models.*
import com.nicholasbrooking.pkg.schachfish.domain.models.board.*
import com.nicholasbrooking.pkg.schachfish.domain.models.move.MoveCollectionDto
import com.nicholasbrooking.pkg.schachfish.domain.models.move.MoveDto
import com.nicholasbrooking.pkg.schachfish.domain.models.pieces.PieceCreationDto
import com.nicholasbrooking.pkg.schachfish.domain.util.BoardStateDtoBuilder
import com.nicholasbrooking.pkg.schachfish.service.exception.SchachfishInvalidInput


fun com.nicholasbrooking.pkg.schachfish.api.model.BoardStateDto?.toInternalDto(): BoardStateDto {
    if (this == null) {
        throw SchachfishInvalidInput("No board state given")
    }
    val blackPieces = this.blackStatus?.pieces?.map {
        PieceDtoBuilder.fromPieceCreationDto(it.toInternalCreationDto(Colour.black))
    } ?: throw SchachfishInvalidInput("No black pieces list")
    val whitePieces = this.whiteStatus?.pieces?.map {
        PieceDtoBuilder.fromPieceCreationDto(it.toInternalCreationDto(Colour.white))
    } ?: throw SchachfishInvalidInput("No white pieces list")
    val boardStateCreationDto = BoardStateCreationDto(
            pieceList = listOf(blackPieces, whitePieces).flatten(),
            canCastleDto = buildCanCastleMap(
                    this.blackStatus.canCastleKingSide,
                    this.blackStatus.canCastleQueenSide,
                    this.whiteStatus.canCastleKingSide,
                    this.whiteStatus.canCastleQueenSide
            ),
            history = this.history.map { it.toInternalDto() },
            turn = this.turn?.toInternalEnum() ?: throw SchachfishInvalidInput("Turn not set")
    )
    return BoardStateDtoBuilder.fromCreationDto(boardStateCreationDto)
}

fun com.nicholasbrooking.pkg.schachfish.api.model.MoveCollectionDto.toInternalDto(): MoveCollectionDto {
    return MoveCollectionDto(
            moves = this.moves.map { it.toInternalDto() }
    )
}

fun com.nicholasbrooking.pkg.schachfish.api.model.EnPassantDto?.toInternalDto() = EnPassantDto(
        possible = this?.possible ?: false,
        taken = this?.taken?.toInternalDto()
)

fun com.nicholasbrooking.pkg.schachfish.api.model.PieceDto.toInternalCreationDto(colour: Colour) = PieceCreationDto(
        position = this.position?.toInternalDto() ?: throw SchachfishInvalidInput("Position not set"),
        colour = colour,
        type = this.name?.toInternalEnum() ?: throw SchachfishInvalidInput("Piece name not set")
)

fun com.nicholasbrooking.pkg.schachfish.api.model.Colour.toInternalEnum(): Colour {
    return Colour.stringToEnum(this.value)
}

fun com.nicholasbrooking.pkg.schachfish.api.model.PieceName.toInternalEnum(): PieceType {
    return PieceType.stringToEnum(this.value)
}

fun com.nicholasbrooking.pkg.schachfish.api.model.PositionDto.toInternalDto() = PositionDto(
        x = this.x ?: throw SchachfishInvalidInput("Position x not set"),
        y = this.y ?: throw SchachfishInvalidInput("Position y not set")
)

fun com.nicholasbrooking.pkg.schachfish.api.model.MoveDto?.toInternalDto(): MoveDto {
    if (this == null) {
        throw SchachfishInvalidInput("No board state given")
    }
    return MoveDto(
            from = this.from?.toInternalDto() ?: throw SchachfishInvalidInput("From not set"),
            to = this.to?.toInternalDto() ?: throw SchachfishInvalidInput("To not set"),
            takenPiece = this.takenPiece?.toInternalDto(),
            promoteTo = this.promoteTo?.toInternalEnum()
    )
}

fun buildCanCastleMap(canCastleKingSideBlack: Boolean?,
                      canCastleQueenSideBlack: Boolean?,
                      canCastleKingSideWhite: Boolean?,
                      canCastleQueenSideWhite: Boolean?): Map<Colour, CanCastleDto> {
    val dict = mutableMapOf<Colour, CanCastleDto>()
    dict[Colour.black] = CanCastleDto(
            kingSide = canCastleKingSideBlack ?: throw SchachfishInvalidInput("Castling not set"),
            queenSide = canCastleQueenSideBlack ?: throw SchachfishInvalidInput("Castling not set")
    )
    dict[Colour.white] = CanCastleDto(
            kingSide = canCastleKingSideWhite ?: throw SchachfishInvalidInput("Castling not set"),
            queenSide = canCastleQueenSideWhite ?: throw SchachfishInvalidInput("Castling not set")
    )
    return dict
}
