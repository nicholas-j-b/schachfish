package com.nicholasbrooking.pkg.schachfish.domain.util

import com.nicholasbrooking.pkg.schachfish.domain.models.board.BoardStateCreationDto
import com.nicholasbrooking.pkg.schachfish.domain.models.pieces.*
import com.nicholasbrooking.pkg.schachfish.domain.models.board.BoardStateDto

class BoardStateDtoBuilder {
    companion object {
        fun fromCreationDto(boardStateCreationDto: BoardStateCreationDto): BoardStateDto {
            val pieceMatrix: Array<Array<PieceDto?>> = Array(size = 8) { Array<PieceDto?>(size = 8) { null } }
            addPiecesToMatrix(boardStateCreationDto.pieceList, pieceMatrix)
            return BoardStateDto(
                    pieceMatrix = pieceMatrix,
                    canCastleDto = boardStateCreationDto.canCastleDto,
                    history = boardStateCreationDto.history,
                    turn = boardStateCreationDto.turn
            )
        }

        fun addPiecesToMatrix(pieceList: List<PieceDto>, pieceMatrix: Array<Array<PieceDto?>>) {
            pieceList.forEach {
                pieceMatrix[it.position.x][it.position.y] = it
            }
        }
    }
}
