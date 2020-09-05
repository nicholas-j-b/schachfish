package com.nicholasbrooking.pkg.schachfish.service.mapper

import com.nicholasbrooking.pkg.schachfish.domain.util.PieceDtoBuilder
import com.nicholasbrooking.pkg.schachfish.domain.models.*
import com.nicholasbrooking.pkg.schachfish.domain.models.board.*
import com.nicholasbrooking.pkg.schachfish.domain.models.move.MoveDto
import com.nicholasbrooking.pkg.schachfish.domain.models.pieces.PieceCreationDto
import com.nicholasbrooking.pkg.schachfish.domain.util.BoardStateDtoBuilder
import com.nicholasbrooking.pkg.schachfish.service.exception.SchachfishInvalidInput


fun com.nicholasbrooking.pkg.schachfish.api.models.BoardStateDto.toInternalEntity(): BoardStateDto {
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
            enPassantDto = this.enPassant.toInternalDto(),
            turn = this.turn?.toInternalEnum() ?: throw SchachfishInvalidInput("Turn not set")
    )
    return BoardStateDtoBuilder.fromCreationDto(boardStateCreationDto)
}

fun com.nicholasbrooking.pkg.schachfish.api.models.EnPassantDto?.toInternalDto() = EnPassantDto(
        possible = this?.possible ?: false,
        taken = this?.taken?.toInternalDto()
)

fun com.nicholasbrooking.pkg.schachfish.api.models.PieceDto.toInternalCreationDto(colour: Colour) = PieceCreationDto(
        position = this.position?.toInternalDto() ?: throw SchachfishInvalidInput("Position not set"),
        colour = colour,
        type = this.name?.toInternalEnum() ?: throw SchachfishInvalidInput("Piece name not set")
)

fun com.nicholasbrooking.pkg.schachfish.api.models.Colour.toInternalEnum(): Colour {
    return Colour.stringToEnum(this.value)
}

fun com.nicholasbrooking.pkg.schachfish.api.models.PieceName.toInternalEnum(): PieceType {
    return PieceType.stringToEnum(this.value)
}

fun com.nicholasbrooking.pkg.schachfish.api.models.PositionDto.toInternalDto() = PositionDto(
        x = this.x ?: throw SchachfishInvalidInput("Position x not set"),
        y = this.y ?: throw SchachfishInvalidInput("Position y not set")
)

fun com.nicholasbrooking.pkg.schachfish.api.models.MoveDto.toInternalDto() = MoveDto(
        from = this.from?.toInternalDto() ?: throw SchachfishInvalidInput("From not set"),
        to = this.to?.toInternalDto() ?: throw SchachfishInvalidInput("To not set"),
        takenPiece = this.takenPiece?.toInternalDto(),
        promoteTo = this.promoteTo?.toInternalEnum()
)

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
