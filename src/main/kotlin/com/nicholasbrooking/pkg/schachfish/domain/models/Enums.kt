package com.nicholasbrooking.pkg.schachfish.domain.models

enum class Colour {
    white, black;
    companion object {
        fun stringToEnum(s: String): Colour {
            return Colour.values().first {
                it.toString() == s
            }
        }

        fun getOtherColour(colour: Colour): Colour = if (colour == white) black else white
    }
}

enum class PieceType {
    king, queen, rook, bishop, knight, pawn;
    companion object {
        fun stringToEnum(name: String): PieceType {
            return PieceType.values().first {
                it.toString() == name
            }
        }
    }
}
