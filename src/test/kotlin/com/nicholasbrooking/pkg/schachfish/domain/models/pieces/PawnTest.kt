package com.nicholasbrooking.pkg.schachfish.domain.models.pieces

import com.nicholasbrooking.pkg.schachfish.domain.util.PieceDtoBuilder
import com.nicholasbrooking.pkg.schachfish.domain.models.*
import com.nicholasbrooking.pkg.schachfish.domain.models.board.PositionDto
import com.nicholasbrooking.pkg.schachfish.domain.models.pieces.util.getDefaultPawn
import com.nicholasbrooking.pkg.schachfish.domain.models.pieces.util.getDefaultPieceOnBoard
import com.nicholasbrooking.pkg.schachfish.service.board.BoardService
import com.nicholasbrooking.pkg.schachfish.service.piece.PawnService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class PawnTest(
        @Autowired private val pawnService: PawnService,
        @Autowired private val boardService: BoardService
) {
    companion object {
        val DEFAULT_COLOUR = Colour.black
        val PIECE_TYPE = PieceType.pawn
    }

    @Test
    fun `instantiate pawn`() {
        val positionDto = PositionDto(0, 1)
        val pawnDto = PieceCreationDto(
                positionDto,
                DEFAULT_COLOUR,
                PIECE_TYPE
        )

        val pawn = PieceDtoBuilder.fromPieceCreationDto(pawnDto)

        assertThat(pawn).isNotNull
        assertThat(pawn.position).isEqualTo(positionDto)
        assertThat(pawn.colour).isEqualTo(DEFAULT_COLOUR)
        assertThat(pawn.pieceName).isEqualTo("pawn")
    }

    @Test
    fun `find pawn legal moves on empty board`() {
        val (pawnDto, boardState) = getDefaultPieceOnBoard(PIECE_TYPE)

        val legalMoves = pawnService.getLegalMoves(pawnDto, boardState)

        assertThat(legalMoves.size).isEqualTo(1)
        assertThat(legalMoves.first().from.x).isEqualTo(pawnDto.position.x)
        assertThat(legalMoves.first().from.y).isEqualTo(pawnDto.position.y)
        assertThat(legalMoves.first().to.x).isEqualTo(pawnDto.position.x)
        assertThat(legalMoves.first().to.y).isEqualTo(pawnDto.position.y - 1)
    }

    @Test
    fun `pawn blocked by piece in front`() {
        val (testPawnDto, boardState) = getDefaultPieceOnBoard(PIECE_TYPE, positionDto =  PositionDto(4, 4))
        val opponentPawnDto = getDefaultPawn(PIECE_TYPE, Colour.getOtherColour(DEFAULT_COLOUR), positionDto = PositionDto(4, 3))
        boardService.addPiecesToBoard(listOf(opponentPawnDto), boardState)

        val legalMoves = pawnService.getLegalMoves(testPawnDto, boardState)

        assertThat(legalMoves.size).isEqualTo(0)
    }

    @Test
    fun `pawn can move two squares from starting position`() {
        val (pawnDto, boardState) = getDefaultPieceOnBoard(PIECE_TYPE, positionDto =  PositionDto(4, 6))

        val legalMoves = pawnService.getLegalMoves(pawnDto, boardState)

        assertThat(legalMoves.size).isEqualTo(2)
    }

    @Test
    fun `pawn can take diagonally`() {
        val (testPawnDto, boardState) = getDefaultPieceOnBoard(PIECE_TYPE, positionDto =  PositionDto(4, 4))
        val opponentPawnDto1 = getDefaultPawn(PIECE_TYPE, Colour.getOtherColour(DEFAULT_COLOUR), positionDto = PositionDto(3, 3))
        val opponentPawnDto2 = getDefaultPawn(PIECE_TYPE, Colour.getOtherColour(DEFAULT_COLOUR), positionDto = PositionDto(5, 3))
        boardService.addPiecesToBoard(listOf(opponentPawnDto1, opponentPawnDto2), boardState)

        val legalMoves = pawnService.getLegalMoves(testPawnDto, boardState)

        assertThat(legalMoves.size).isEqualTo(3)
    }

    @Test
    fun `pawn promotion options found`() {
        val (pawnDto, boardState) = getDefaultPieceOnBoard(PIECE_TYPE, positionDto =  PositionDto(4, 1))

        val legalMoves = pawnService.getLegalMoves(pawnDto, boardState)

        assertThat(legalMoves.size).isEqualTo(4)
    }

    @Test
    fun `pawn makes standard move`() {
        val (pawnDto, boardState) = getDefaultPieceOnBoard(PIECE_TYPE)

        val legalMoves = pawnService.getLegalMoves(pawnDto, boardState)

        assertThat(legalMoves.size).isEqualTo(1)
    }

}