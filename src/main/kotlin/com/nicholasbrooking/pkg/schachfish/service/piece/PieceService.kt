package com.nicholasbrooking.pkg.schachfish.service.piece

import com.nicholasbrooking.pkg.schachfish.domain.models.board.BoardStateDto
import com.nicholasbrooking.pkg.schachfish.domain.models.board.PositionDto
import com.nicholasbrooking.pkg.schachfish.domain.models.move.MoveDto
import com.nicholasbrooking.pkg.schachfish.domain.models.pieces.PieceDto

interface PieceService {
    fun getLegalMoves(pieceDto: PieceDto, boardStateDto: BoardStateDto): List<MoveDto>
    fun move(pieceDto: PieceDto, to: PositionDto)
}