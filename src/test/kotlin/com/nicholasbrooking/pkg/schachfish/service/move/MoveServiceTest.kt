package com.nicholasbrooking.pkg.schachfish.service.move

import com.nicholasbrooking.pkg.schachfish.domain.models.Colour
import com.nicholasbrooking.pkg.schachfish.domain.models.PieceType
import com.nicholasbrooking.pkg.schachfish.domain.models.board.PositionDto
import com.nicholasbrooking.pkg.schachfish.domain.models.move.MoveDto
import com.nicholasbrooking.pkg.schachfish.domain.models.pieces.PieceCreationDto
import com.nicholasbrooking.pkg.schachfish.domain.models.pieces.PieceDto
import com.nicholasbrooking.pkg.schachfish.domain.models.pieces.util.getDefaultPieceOnBoard
import com.nicholasbrooking.pkg.schachfish.domain.models.pieces.util.getEmptyBoard
import com.nicholasbrooking.pkg.schachfish.domain.util.PieceDtoBuilder
import com.nicholasbrooking.pkg.schachfish.service.board.ActiveBoardService
import com.nicholasbrooking.pkg.schachfish.service.board.BoardService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class MoveServiceTest(
        @Autowired private val moveService: MoveService,
        @Autowired private val activeBoardService: ActiveBoardService,
        @Autowired private val boardService: BoardService,
        @Autowired private val moveFinderService: MoveFinderService
) {
    companion object {
        val DEFAULT_COLOUR = Colour.black
        var boardId = 0L;
    }

    @BeforeEach
    fun `set up board state`() {
        val board = getEmptyBoard()
        boardId = activeBoardService.createBoard(board)
    }

    @AfterEach
    fun `tear down board`() {
        activeBoardService.deleteBoard(boardId)
    }

    @Test
    fun `move pawn on empty board`() {
        val startPosition = PositionDto(4, 4)
        val endPosition = PositionDto(4, 5)
        val (pieceDto, boardState) = getDefaultPieceOnBoard(PieceType.pawn, DEFAULT_COLOUR, startPosition)
        activeBoardService.updateBoardState(boardId, boardState)
        val moveDto = MoveDto(
                from = startPosition,
                to = endPosition,
                takenPiece = null,
                promoteTo = null
        )

        moveService.makeMove(moveDto, boardId)

        val boardStateAfterMove = activeBoardService.getBoardState(boardId)
        assertThat(boardStateAfterMove.pieceMatrix[4][4]).isNull()
        assertThat(boardStateAfterMove.pieceMatrix[4][5]).isNotNull
    }

    @Test
    fun `take piece with pawn`() {
        val whitePawn = getWhitePawnCentre()
        val blackPawn = getBlackOpposingPawn()
        val boardState = activeBoardService.getBoardState(boardId)
        boardService.addPiecesToBoard(listOf(whitePawn, blackPawn), boardState)
        activeBoardService.updateBoardState(boardId, boardState)
        val moveCollection = moveFinderService.getLegalMoves(boardId)
        val moveToTest = moveCollection.moves.first{ moveDto ->
            moveDto.takenPiece != null
        }
        assertThat(moveToTest.from).isEqualTo(whitePawn.position)
        assertThat(moveToTest.to).isEqualTo(blackPawn.position)

        moveService.makeMove(moveToTest, boardId)

        val boardStateAfterMove = activeBoardService.getBoardState(boardId)
        assertThat(boardStateAfterMove).isNotNull
        assertThat(boardStateAfterMove.pieceMatrix[4][4]).isNull()
        assertThat(boardStateAfterMove.pieceMatrix[5][5]!!.colour).isEqualTo(Colour.white)
    }

    private fun getWhitePawnCentre(): PieceDto {
        val startPosition = PositionDto(4, 4)
        val pawnDto = PieceCreationDto(
                startPosition,
                Colour.white,
                PieceType.pawn
        )

        return PieceDtoBuilder.fromPieceCreationDto(pawnDto)
    }

    private fun getBlackOpposingPawn(): PieceDto {
        val startPosition = PositionDto(5, 5)
        val pawnDto = PieceCreationDto(
                startPosition,
                Colour.black,
                PieceType.pawn
        )

        return PieceDtoBuilder.fromPieceCreationDto(pawnDto)
    }
}