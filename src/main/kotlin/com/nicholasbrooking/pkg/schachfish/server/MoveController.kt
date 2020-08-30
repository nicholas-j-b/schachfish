package com.nicholasbrooking.pkg.schachfish.server

import com.nicholasbrooking.pkg.schachfish.api.models.BoardStateDto
import com.nicholasbrooking.pkg.schachfish.api.models.MoveCollectionDto
import com.nicholasbrooking.pkg.schachfish.service.move.MoveFinderService
import com.nicholasbrooking.pkg.schachfish.service.exception.SchachfishInvalidInput
import com.nicholasbrooking.pkg.schachfish.service.mapper.toApiDto
import com.nicholasbrooking.pkg.schachfish.service.mapper.toInternalEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@Controller
class MoveController(
        private val moveFinderService: MoveFinderService
) {

    @RequestMapping("/moves/{boardId}")
    fun getLegalMovesFromBoardState(@PathVariable("boardId") boardId: String): ResponseEntity<MoveCollectionDto> {
        try {
//            val legalMoves = moveFinderService.getLegalMoves(boardStateDto.toInternalEntity())
//            return ResponseEntity.ok(legalMoves.toApiDto())
            return ResponseEntity.ok(MoveCollectionDto())
        } catch (e: SchachfishInvalidInput) {
            throw ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Boardstate did not internalise", e
            )
        }
    }
}


