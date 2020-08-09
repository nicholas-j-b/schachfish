package com.nicholasbrooking.pkg.schachfish.domain.models.pieces

import com.nicholasbrooking.pkg.schachfish.domain.util.PieceDtoBuilder
import com.nicholasbrooking.pkg.schachfish.domain.models.*
import com.nicholasbrooking.pkg.schachfish.domain.models.board.PositionDto
import com.nicholasbrooking.pkg.schachfish.domain.models.pieces.util.getDefaultPieceOnBoard
import com.nicholasbrooking.pkg.schachfish.service.board.BoardService
import com.nicholasbrooking.pkg.schachfish.service.piece.BishopService
import com.nicholasbrooking.pkg.schachfish.service.piece.QueenService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class QueenTest(
        @Autowired private val queenService: QueenService
) {
    companion object {
        val DEFAULT_COLOUR = Colour.black
        val PIECE_TYPE = PieceType.queen
    }

    @Test
    fun `instantiate queen`() {
        val positionDto = PositionDto(0, 1)
        val pieceDto = PieceCreationDto(
                positionDto,
                DEFAULT_COLOUR,
                PIECE_TYPE
        )

        val queen = PieceDtoBuilder.fromPieceCreationDto(pieceDto)

        assertThat(queen).isNotNull
        assertThat(queen.position).isEqualTo(positionDto)
        assertThat(queen.colour).isEqualTo(DEFAULT_COLOUR)
        assertThat(queen.pieceName).isEqualTo("queen")
    }

    @Test
    fun `find queen legal moves on empty board in corner`() {
        val (pieceDto, boardState) = getDefaultPieceOnBoard(
                PIECE_TYPE,
                positionDto = PositionDto(0, 0)
        )

        val legalMoves = queenService.getLegalMoves(pieceDto, boardState)

        assertThat(legalMoves.size).isEqualTo(21)
    }

    @Test
    fun `find queen legal moves on empty board in middle`() {
        val (pieceDto, boardState) = getDefaultPieceOnBoard(
                PIECE_TYPE,
                positionDto = PositionDto(4, 4)
        )

        val legalMoves = queenService.getLegalMoves(pieceDto, boardState)

        assertThat(legalMoves.size).isEqualTo(27)
    }
}