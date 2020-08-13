package com.nicholasbrooking.pkg.schachfish.domain.models.pieces

import com.nicholasbrooking.pkg.schachfish.domain.util.PieceDtoBuilder
import com.nicholasbrooking.pkg.schachfish.domain.models.*
import com.nicholasbrooking.pkg.schachfish.domain.models.board.PositionDto
import com.nicholasbrooking.pkg.schachfish.domain.models.pieces.util.getDefaultPieceOnBoard
import com.nicholasbrooking.pkg.schachfish.service.piece.KingService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class KingTest(
        @Autowired private val kingService: KingService
) {
    companion object {
        val DEFAULT_COLOUR = Colour.black
        val PIECE_TYPE = PieceType.king
    }

    @Test
    fun `instantiate king`() {
        val positionDto = PositionDto(0, 1)
        val pieceDto = PieceCreationDto(
                positionDto,
                DEFAULT_COLOUR,
                PIECE_TYPE
        )

        val king = PieceDtoBuilder.fromPieceCreationDto(pieceDto)

        assertThat(king).isNotNull
        assertThat(king.position).isEqualTo(positionDto)
        assertThat(king.colour).isEqualTo(DEFAULT_COLOUR)
        assertThat(king.pieceName).isEqualTo("king")
    }

    @Test
    fun `find king legal moves on empty board in corner`() {
        val (pieceDto, boardState) = getDefaultPieceOnBoard(
                PIECE_TYPE,
                positionDto = PositionDto(0, 0)
        )

        val legalMoves = kingService.getLegalMoves(pieceDto, boardState)

        assertThat(legalMoves.size).isEqualTo(3)
    }

    @Test
    fun `find king legal moves on empty board in middle`() {
        val (pieceDto, boardState) = getDefaultPieceOnBoard(
                PIECE_TYPE,
                positionDto = PositionDto(4, 4)
        )

        val legalMoves = kingService.getLegalMoves(pieceDto, boardState)

        assertThat(legalMoves.size).isEqualTo(8)
    }

    @Test
    fun `find king legal moves on empty board on edge`() {
        val (pieceDto, boardState) = getDefaultPieceOnBoard(
                PIECE_TYPE,
                positionDto = PositionDto(0, 4)
        )

        val legalMoves = kingService.getLegalMoves(pieceDto, boardState)

        assertThat(legalMoves.size).isEqualTo(5)
    }
}