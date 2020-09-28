package com.nicholasbrooking.pkg.schachfish.service.configuration

import com.nicholasbrooking.pkg.schachfish.domain.models.Colour
import com.nicholasbrooking.pkg.schachfish.domain.models.PieceType
import com.nicholasbrooking.pkg.schachfish.domain.models.configuration.PieceConfiguration
import com.nicholasbrooking.pkg.schachfish.domain.models.configuration.bishop.BlackBishopConfiguration
import com.nicholasbrooking.pkg.schachfish.domain.models.configuration.bishop.WhiteBishopConfiguration
import com.nicholasbrooking.pkg.schachfish.domain.models.configuration.king.BlackKingConfiguration
import com.nicholasbrooking.pkg.schachfish.domain.models.configuration.king.KingConfiguration
import com.nicholasbrooking.pkg.schachfish.domain.models.configuration.king.WhiteKingConfiguration
import com.nicholasbrooking.pkg.schachfish.domain.models.configuration.knight.BlackKnightConfiguration
import com.nicholasbrooking.pkg.schachfish.domain.models.configuration.knight.WhiteKnightConfiguration
import com.nicholasbrooking.pkg.schachfish.domain.models.configuration.pawn.BlackPawnConfiguration
import com.nicholasbrooking.pkg.schachfish.domain.models.configuration.pawn.WhitePawnConfiguration
import com.nicholasbrooking.pkg.schachfish.domain.models.configuration.queen.BlackQueenConfiguration
import com.nicholasbrooking.pkg.schachfish.domain.models.configuration.queen.WhiteQueenConfiguration
import com.nicholasbrooking.pkg.schachfish.domain.models.configuration.rook.BlackRookConfiguration
import com.nicholasbrooking.pkg.schachfish.domain.models.configuration.rook.WhiteRookConfiguration
import org.springframework.stereotype.Service

@Service
class PieceConfigurationService {
    fun fromColourAndPieceType(colour: Colour, pieceType: PieceType): PieceConfiguration {
        return when (pieceType) {
            PieceType.pawn -> when (colour) {
                Colour.black -> BlackPawnConfiguration
                Colour.white -> WhitePawnConfiguration
            }
            PieceType.rook -> when (colour) {
                Colour.black -> BlackRookConfiguration
                Colour.white -> WhiteRookConfiguration
            }
            PieceType.bishop -> when (colour) {
                Colour.black -> BlackBishopConfiguration
                Colour.white -> WhiteBishopConfiguration
            }
            PieceType.queen -> when (colour) {
                Colour.black -> BlackQueenConfiguration
                Colour.white -> WhiteQueenConfiguration
            }
            PieceType.knight -> when (colour) {
                Colour.black -> BlackKnightConfiguration
                Colour.white -> WhiteKnightConfiguration
            }
            PieceType.king -> when (colour) {
                Colour.black -> BlackKingConfiguration
                Colour.white -> WhiteKingConfiguration
            }
        }
    }

}