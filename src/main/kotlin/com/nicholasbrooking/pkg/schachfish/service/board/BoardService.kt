package com.nicholasbrooking.pkg.schachfish.service.board

import com.nicholasbrooking.pkg.schachfish.domain.models.board.BoardStateDto
import com.nicholasbrooking.pkg.schachfish.domain.models.board.PositionDto
import com.nicholasbrooking.pkg.schachfish.domain.models.pieces.PieceDto
import com.nicholasbrooking.pkg.schachfish.domain.util.BoardStateDtoBuilder
import org.springframework.stereotype.Service

@Service
class BoardService(
) {
    fun getPiece(positionDto: PositionDto, boardStateDto: BoardStateDto): PieceDto? {
        return boardStateDto.pieceMatrix[positionDto.x][positionDto.y]
    }

    fun addPiecesToBoard(pieces: List<PieceDto>, boardStateDto: BoardStateDto){
        BoardStateDtoBuilder.addPiecesToMatrix(pieces, boardStateDto.pieceMatrix)
    }

    fun removePiece(position: PositionDto, boardStateDto: BoardStateDto) {
        boardStateDto.pieceMatrix[position.x][position.y] = null
        //TODO("add to taken pieces")
    }
}