package com.nicholasbrooking.pkg.schachfish.domain.models.pieces

import com.nicholasbrooking.pkg.schachfish.domain.util.PieceDtoBuilder
import com.nicholasbrooking.pkg.schachfish.domain.models.*
import com.nicholasbrooking.pkg.schachfish.domain.models.board.PositionDto
import com.nicholasbrooking.pkg.schachfish.domain.models.pieces.util.getDefaultPieceOnBoard
import com.nicholasbrooking.pkg.schachfish.service.piece.BishopService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class BishopTest(
        @Autowired private val bishopService: BishopService
) {
    companion object {
        val DEFAULT_COLOUR = Colour.black
        val PIECE_TYPE = PieceType.bishop
    }

    @Test
    fun `instantiate bishop`() {
        val positionDto = PositionDto(0, 1)
        val pieceDto = PieceCreationDto(
                positionDto,
                DEFAULT_COLOUR,
                PIECE_TYPE
        )

        val bishop = PieceDtoBuilder.fromPieceCreationDto(pieceDto)

        assertThat(bishop).isNotNull
        assertThat(bishop.position).isEqualTo(positionDto)
        assertThat(bishop.colour).isEqualTo(DEFAULT_COLOUR)
        assertThat(bishop.pieceName).isEqualTo("bishop")
    }

    @Test
    fun `find bishop legal moves on empty board in corner`() {
        val (pieceDto, boardState) = getDefaultPieceOnBoard(
                PIECE_TYPE,
                positionDto = PositionDto(0, 0)
        )

        val legalMoves = bishopService.getLegalMoves(pieceDto, boardState)

        assertThat(legalMoves.size).isEqualTo(7)
    }

    @Test
    fun `find bishop legal moves on empty board in middle`() {
        val (pieceDto, boardState) = getDefaultPieceOnBoard(
                PIECE_TYPE,
                positionDto = PositionDto(4, 4)
        )

        val legalMoves = bishopService.getLegalMoves(pieceDto, boardState)

        assertThat(legalMoves.size).isEqualTo(13)
    }
}