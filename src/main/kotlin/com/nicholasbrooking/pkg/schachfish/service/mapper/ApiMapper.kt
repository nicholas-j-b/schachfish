package com.nicholasbrooking.pkg.schachfish.service.mapper

import com.nicholasbrooking.pkg.schachfish.api.models.MoveCollectionDto
import com.nicholasbrooking.pkg.schachfish.api.models.MoveDto
import com.nicholasbrooking.pkg.schachfish.api.models.PositionDto


fun com.nicholasbrooking.pkg.schachfish.domain.models.MoveCollectionDto.toApiDto(): MoveCollectionDto {
    return MoveCollectionDto(
            moves = this.moves.map {
                it.toApiDto()
            }.toTypedArray())
}

fun com.nicholasbrooking.pkg.schachfish.domain.models.MoveDto.toApiDto(): MoveDto {
    return MoveDto(
            to = this.to.toApiDto()
    )
}

fun com.nicholasbrooking.pkg.schachfish.domain.models.PositionDto.toApiDto(): PositionDto {
    return PositionDto(
            x = this.x,
            y = this.y
    )
}
