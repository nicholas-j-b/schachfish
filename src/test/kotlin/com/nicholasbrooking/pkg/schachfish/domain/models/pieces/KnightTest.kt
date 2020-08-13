package com.nicholasbrooking.pkg.schachfish.domain.models.pieces

import com.nicholasbrooking.pkg.schachfish.domain.util.PieceDtoBuilder
import com.nicholasbrooking.pkg.schachfish.domain.models.*
import com.nicholasbrooking.pkg.schachfish.domain.models.board.PositionDto
import com.nicholasbrooking.pkg.schachfish.domain.models.pieces.util.getDefaultPieceOnBoard
import com.nicholasbrooking.pkg.schachfish.service.piece.KnightService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class KnightTest(
        @Autowired private val knightService: KnightService
) {
    companion object {
        val DEFAULT_COLOUR = Colour.black
        val PIECE_TYPE = PieceType.knight
    }

    @Test
    fun `instantiate knight`() {
        val positionDto = PositionDto(0, 1)
        val pieceDto = PieceCreationDto(
                positionDto,
                DEFAULT_COLOUR,
                PIECE_TYPE
        )

        val knight = PieceDtoBuilder.fromPieceCreationDto(pieceDto)

        assertThat(knight).isNotNull
        assertThat(knight.position).isEqualTo(positionDto)
        assertThat(knight.colour).isEqualTo(DEFAULT_COLOUR)
        assertThat(knight.pieceName).isEqualTo("knight")
    }

    @Test
    fun `find knight legal moves on empty board in corner`() {
        val (pieceDto, boardState) = getDefaultPieceOnBoard(
                PIECE_TYPE,
                positionDto = PositionDto(0, 0)
        )

        val legalMoves = knightService.getLegalMoves(pieceDto, boardState)

        assertThat(legalMoves.size).isEqualTo(2)
    }

    @Test
    fun `find knight legal moves on empty board in middle`() {
        val (pieceDto, boardState) = getDefaultPieceOnBoard(
                PIECE_TYPE,
                positionDto = PositionDto(4, 4)
        )

        val legalMoves = knightService.getLegalMoves(pieceDto, boardState)

        assertThat(legalMoves.size).isEqualTo(8)
    }

    @Test
    fun `find knight legal moves on empty board near edge`() {
        val (pieceDto, boardState) = getDefaultPieceOnBoard(
                PIECE_TYPE,
                positionDto = PositionDto(1, 4)
        )

        val legalMoves = knightService.getLegalMoves(pieceDto, boardState)

        assertThat(legalMoves.size).isEqualTo(6)
    }
}