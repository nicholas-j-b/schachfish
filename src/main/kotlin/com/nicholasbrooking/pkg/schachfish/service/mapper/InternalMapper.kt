package com.nicholasbrooking.pkg.schachfish.service.mapper

import com.nicholasbrooking.pkg.schachfish.domain.entities.Piece
import com.nicholasbrooking.pkg.schachfish.domain.entities.PieceBuilder
import com.nicholasbrooking.pkg.schachfish.domain.models.*
import com.nicholasbrooking.pkg.schachfish.service.exception.InvalidInputException


fun com.nicholasbrooking.pkg.schachfish.api.models.BoardStateDto.toInternalDto() = BoardStateDto(
        pieceMatrix = buildPieceMatrix(this.blackStatus?.pieces, this.whiteStatus?.pieces),
        canCastleDto = buildCanCastleMap(
                this.blackStatus?.canCastleKingSide,
                this.blackStatus?.canCastleQueenSide,
                this.whiteStatus?.canCastleKingSide,
                this.whiteStatus?.canCastleQueenSide
        ),
        enPassantDto = this.enPassant.toInternalDto(),
        turn = this.turn?.toInternalEnum() ?: throw InvalidInputException("Turn not set")
)

fun com.nicholasbrooking.pkg.schachfish.api.models.EnPassantDto?.toInternalDto() = EnPassantDto(
        possible = this?.possible ?: false,
        taken = this?.taken?.toInternalDto()
)


fun com.nicholasbrooking.pkg.schachfish.api.models.PieceDto.toInternalDto(colour: Colour) = PieceDto(
        position = this.position?.toInternalDto() ?: throw InvalidInputException("Position not set"),
        colour = colour,
        type = this.name?.toInternalEnum() ?: throw InvalidInputException("Piece name not set")
)

fun com.nicholasbrooking.pkg.schachfish.api.models.Colour.toInternalEnum(): Colour {
    return Colour.stringToEnum(this.value)
}

fun com.nicholasbrooking.pkg.schachfish.api.models.PieceName.toInternalEnum(): PieceType {
    return PieceType.stringToEnum(this.value)
}

fun com.nicholasbrooking.pkg.schachfish.api.models.PositionDto.toInternalDto() = PositionDto(
        x = this.x ?: throw InvalidInputException("Position x not set"),
        y = this.y ?: throw InvalidInputException("Position y not set")
)

fun buildCanCastleMap(canCastleKingSideBlack: Boolean?,
                      canCastleQueenSideblack: Boolean?,
                      canCastleKingSideWhite: Boolean?,
                      canCastleQueenSideWhite: Boolean?): Map<Colour, CanCastleDto> {
    val dict = mutableMapOf<Colour, CanCastleDto>()
    dict.set(Colour.black, CanCastleDto(
            kingSide = canCastleKingSideBlack ?: throw InvalidInputException("Castling not set"),
            queenSide = canCastleQueenSideblack ?: throw InvalidInputException("Castling not set")
    ))
    dict.set(Colour.white, CanCastleDto(
            kingSide = canCastleKingSideWhite ?: throw InvalidInputException("Castling not set"),
            queenSide = canCastleQueenSideWhite ?: throw InvalidInputException("Castling not set")
    ))
    return dict
}

fun buildPieceMatrix(
        blackPieces: Array<com.nicholasbrooking.pkg.schachfish.api.models.PieceDto>?,
        whitePieces: Array<com.nicholasbrooking.pkg.schachfish.api.models.PieceDto>?)
        : Array<Array<Piece?>> {
    val pieceMatrix: Array<Array<Piece?>> = Array(size = 8) { Array<Piece?>(size = 8) { null } }
    blackPieces?.forEach { pieceDto ->
        addPieceToMatrix(pieceDto, pieceDto.position, pieceMatrix, Colour.black)
    }
    whitePieces?.forEach { pieceDto ->
        addPieceToMatrix(pieceDto, pieceDto.position, pieceMatrix, Colour.white)
    }
    return pieceMatrix
}

private fun addPieceToMatrix(
        pieceDto: com.nicholasbrooking.pkg.schachfish.api.models.PieceDto,
        position: com.nicholasbrooking.pkg.schachfish.api.models.PositionDto?,
        pieceMatrix: Array<Array<Piece?>>, colour: Colour) {
    pieceDto.position?.y?.let {
        position?.x?.let { pieceMatrix.getOrNull(it) }
                ?.set(it, PieceBuilder.fromPieceDto(pieceDto.toInternalDto(colour)))
    }
}


