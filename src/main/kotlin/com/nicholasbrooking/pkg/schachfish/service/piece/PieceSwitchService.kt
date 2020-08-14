package com.nicholasbrooking.pkg.schachfish.service.piece

import com.nicholasbrooking.pkg.schachfish.domain.models.PieceType
import com.nicholasbrooking.pkg.schachfish.domain.models.PieceType.*
import org.springframework.stereotype.Service

@Service
class PieceSwitchService (
        private val pawnService: PawnService,
        private val knightService: KnightService,
        private val bishopService: BishopService,
        private val rookService: RookService,
        private val queenService: QueenService,
        private val kingService: KingService
) {
    fun getService(pieceType: PieceType): PieceService {
        return when (pieceType) {
            pawn -> pawnService
            knight -> knightService
            bishop -> bishopService
            rook -> rookService
            queen -> queenService
            king -> kingService
        }
    }
}
