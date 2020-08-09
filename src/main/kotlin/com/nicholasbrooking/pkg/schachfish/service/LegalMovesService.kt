package com.nicholasbrooking.pkg.schachfish.service

import com.nicholasbrooking.pkg.schachfish.domain.models.BoardStateDto
import com.nicholasbrooking.pkg.schachfish.domain.models.MoveCollectionDto
import com.nicholasbrooking.pkg.schachfish.domain.models.MoveDto
import com.nicholasbrooking.pkg.schachfish.domain.models.PositionDto
import org.springframework.stereotype.Service

@Service
class LegalMovesService {
    fun getLegalMoves(boardStateDto: BoardStateDto): MoveCollectionDto {
        println(boardStateDto)
        return MoveCollectionDto(listOf(MoveDto(
                PositionDto(3, 3),
                PositionDto(3, 5),
                null
        )))
    }

}