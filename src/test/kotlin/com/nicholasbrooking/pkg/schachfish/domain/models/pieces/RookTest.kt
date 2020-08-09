package com.nicholasbrooking.pkg.schachfish.domain.models.pieces

import com.nicholasbrooking.pkg.schachfish.domain.util.PieceDtoBuilder
import com.nicholasbrooking.pkg.schachfish.domain.models.*
import com.nicholasbrooking.pkg.schachfish.domain.models.board.PositionDto
import com.nicholasbrooking.pkg.schachfish.domain.models.pieces.util.getDefaultPawn
import com.nicholasbrooking.pkg.schachfish.domain.models.pieces.util.getDefaultPieceOnBoard
import com.nicholasbrooking.pkg.schachfish.service.board.BoardService
import com.nicholasbrooking.pkg.schachfish.service.piece.PawnService
import com.nicholasbrooking.pkg.schachfish.service.piece.RookService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class RookTest(
        @Autowired private val rookService: RookService,
        @Autowired private val boardService: BoardService
) {
    companion object {
        val DEFAULT_COLOUR = Colour.black
        val PIECE_TYPE = PieceType.rook
    }

    @Test
    fun `instantiate rook`() {
        val positionDto = PositionDto(0, 1)
        val pieceDto = PieceCreationDto(
                positionDto,
                DEFAULT_COLOUR,
                PIECE_TYPE
        )

        val rook = PieceDtoBuilder.fromPieceCreationDto(pieceDto)

        assertThat(rook).isNotNull
        assertThat(rook.position).isEqualTo(positionDto)
        assertThat(rook.colour).isEqualTo(DEFAULT_COLOUR)
        assertThat(rook.pieceName).isEqualTo("rook")
    }

    @Test
    fun `find rook legal moves on empty board`() {
        val (pawnDto, boardState) = getDefaultPieceOnBoard(PIECE_TYPE)

        val legalMoves = rookService.getLegalMoves(pawnDto, boardState)

        assertThat(legalMoves.size).isEqualTo(14)
    }

}