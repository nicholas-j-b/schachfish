package com.nicholasbrooking.pkg.schachfish.domain.entities

import com.nicholasbrooking.pkg.schachfish.domain.models.*

abstract class Piece {
    abstract val colour: Colour
    abstract val id: Int
    abstract val pieceName: String
    abstract var position: PositionDto
//    abstract val startingAmount: Int
//    abstract val boardStateDto: BoardStateDto
//    abstract fun move(moveDto: MoveDto): BoardStateDto
//    abstract fun getLegalMoves(): MoveCollectionDto

//    fun getLegalPositionsRay(startingPosition: Position, direction: Direction, limit: Int? = null, take: Boolean = true): List<Position> {
//        val legalMoveDestinations = mutableListOf<Position>()
//        var positionToTest  = startingPosition
//        loop@ for (i in 0 until (limit ?: 8)) {
//            positionToTest = direction.getNextPosition(positionToTest) ?: break@loop
//            val occupancy = entityManagementService.checkSquareOccupancyType(positionToTest)
//            when {
//                occupancy.isSquare && !occupancy.isOccupied -> legalMoveDestinations.add(positionToTest)
//                occupancy.isSquare && occupancy.colour != colour && take -> { legalMoveDestinations.add(positionToTest); break@loop }
//                else -> break@loop
//            }
//        }
//        return legalMoveDestinations
//    }

//    // assumes valid move
//    fun moveTakesPiece(moveDto: MoveDto): Boolean {
//        val fromOccupancy = entityManagementService.checkSquareOccupancyType(moveDto.from)
//        val toOccupancy = entityManagementService.checkSquareOccupancyType(moveDto.to)
//        return  toOccupancy.isSquare &&
//                toOccupancy.isOccupied &&
//                toOccupancy.colour != fromOccupancy.colour
//    }
}
