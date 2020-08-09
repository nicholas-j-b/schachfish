package com.nicholasbrooking.pkg.schachfish.domain.models.pieces.util

import com.nicholasbrooking.pkg.schachfish.domain.models.Colour
import com.nicholasbrooking.pkg.schachfish.domain.models.PieceType
import com.nicholasbrooking.pkg.schachfish.domain.models.board.BoardStateCreationDto
import com.nicholasbrooking.pkg.schachfish.domain.models.board.BoardStateDto
import com.nicholasbrooking.pkg.schachfish.domain.models.board.PositionDto
import com.nicholasbrooking.pkg.schachfish.domain.models.pieces.PawnTest
import com.nicholasbrooking.pkg.schachfish.domain.models.pieces.PieceCreationDto
import com.nicholasbrooking.pkg.schachfish.domain.models.pieces.PieceDto
import com.nicholasbrooking.pkg.schachfish.domain.util.BoardStateDtoBuilder
import com.nicholasbrooking.pkg.schachfish.domain.util.PieceDtoBuilder

fun getDefaultPieceOnBoard(
        pieceType: PieceType,
        colour: Colour = PawnTest.DEFAULT_COLOUR,
        positionDto: PositionDto = PositionDto(4, 4)
): Pair<PieceDto, BoardStateDto> {
    val pieceDto = getDefaultPawn(pieceType, colour, positionDto)
    val boardState = BoardStateDtoBuilder.fromCreationDto(
            BoardStateCreationDto(
                    listOf(pieceDto)
            )
    )
    return Pair(pieceDto, boardState)
}

fun getDefaultPawn(
        pieceType: PieceType,
        colour: Colour = PawnTest.DEFAULT_COLOUR,
        positionDto: PositionDto = PositionDto(4, 4)
): PieceDto {
    val pieceCreationDto = PieceCreationDto(
            positionDto,
            colour,
            pieceType
    )
    return PieceDtoBuilder.fromPieceCreationDto(pieceCreationDto)
}
