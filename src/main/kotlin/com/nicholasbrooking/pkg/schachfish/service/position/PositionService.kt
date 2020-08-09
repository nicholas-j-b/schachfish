package com.nicholasbrooking.pkg.schachfish.service.position

import com.nicholasbrooking.pkg.schachfish.domain.models.Colour
import com.nicholasbrooking.pkg.schachfish.domain.models.board.BoardStateDto
import com.nicholasbrooking.pkg.schachfish.domain.models.board.PositionDto
import com.nicholasbrooking.pkg.schachfish.domain.models.configuration.BoardConfiguration
import com.nicholasbrooking.pkg.schachfish.domain.models.move.DirectionDto
import com.nicholasbrooking.pkg.schachfish.service.board.BoardService
import org.springframework.stereotype.Service

@Service
class PositionService(
        private val boardService: BoardService
) {
    fun getNextPosition(positionDto: PositionDto, directionDto: DirectionDto): PositionDto? {
        val newPositionDto = PositionDto(
                x = positionDto.x + directionDto.x,
                y = positionDto.y + directionDto.y
        )
        return if (isPositionOnBoard(newPositionDto)) {
            newPositionDto
        } else {
            null
        }
    }

    fun getEmptyPositionRay(
            startingPositionDto: PositionDto,
            directionDto: DirectionDto,
            boardStateDto: BoardStateDto,
            takeColour: Colour?, limit:
            Int? = null): List<PositionDto> {
        var positionToTest = startingPositionDto
        val positionDtos = mutableListOf<PositionDto>()
        loop@ for (i in 0 until (limit ?: 8)) {
            positionToTest = getNextPosition(positionToTest, directionDto) ?: break@loop
            when {
                isSquareEmpty(positionToTest, boardStateDto) -> positionDtos.add(positionToTest)
                takeColour == null -> break@loop
                isSquareOccupiedBy(takeColour, positionToTest, boardStateDto) -> {
                    positionDtos.add(positionToTest)
                    break@loop
                }
            }
        }
        return positionDtos
    }

    fun isSquareOccupiedBy(colour: Colour, positionDto: PositionDto, boardStateDto: BoardStateDto): Boolean {
        return !isSquareEmpty(positionDto, boardStateDto) && boardService.getPiece(positionDto, boardStateDto)?.colour == colour
    }

    fun isSquareEmpty(positionDto: PositionDto, boardStateDto: BoardStateDto): Boolean {
        return boardStateDto.pieceMatrix[positionDto.x][positionDto.y] == null
    }

    private fun isPositionOnBoard(positionDto: PositionDto): Boolean {
        return positionDto.x < BoardConfiguration.boardWidth &&
                positionDto.x >= 0 &&
                positionDto.y < BoardConfiguration.boardHeight &&
                positionDto.y >= 0
    }
}