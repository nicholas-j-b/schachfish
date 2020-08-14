package com.nicholasbrooking.pkg.schachfish.service.move

import com.nicholasbrooking.pkg.schachfish.domain.models.Colour
import com.nicholasbrooking.pkg.schachfish.domain.models.PieceType
import com.nicholasbrooking.pkg.schachfish.domain.models.board.PositionDto
import com.nicholasbrooking.pkg.schachfish.domain.models.move.MoveDto
import com.nicholasbrooking.pkg.schachfish.domain.models.pieces.util.getDefaultPieceOnBoard
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class MoveServiceTest(
        @Autowired private val moveService: MoveService
) {
    companion object {
        val DEFAULT_COLOUR = Colour.black
    }

    @Test
    fun `move pawn on empty board`() {
        val startPosition = PositionDto(4, 4)
        val endPosition = PositionDto(4, 5)
        val (pieceDto, boardState) = getDefaultPieceOnBoard(PieceType.pawn, DEFAULT_COLOUR, startPosition)
        val moveDto = MoveDto(
                from = startPosition,
                to = endPosition,
                takenPiece = null,
                promoteTo = null
        )

        moveService.move(moveDto, boardState)

        assertThat(boardState.pieceMatrix[4][4]).isNull()
        assertThat(boardState.pieceMatrix[4][5]).isNotNull
    }
}