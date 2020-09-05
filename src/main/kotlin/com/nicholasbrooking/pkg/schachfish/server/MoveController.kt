package com.nicholasbrooking.pkg.schachfish.server

import com.nicholasbrooking.pkg.schachfish.api.models.BoardStateDto
import com.nicholasbrooking.pkg.schachfish.api.models.MoveCollectionDto
import com.nicholasbrooking.pkg.schachfish.api.models.MoveDto
import com.nicholasbrooking.pkg.schachfish.service.exception.SchachfishBoardNotFound
import com.nicholasbrooking.pkg.schachfish.service.move.MoveFinderService
import com.nicholasbrooking.pkg.schachfish.service.exception.SchachfishInvalidInput
import com.nicholasbrooking.pkg.schachfish.service.exception.SchachfishInvalidMove
import com.nicholasbrooking.pkg.schachfish.service.mapper.toApiDto
import com.nicholasbrooking.pkg.schachfish.service.mapper.toInternalDto
import com.nicholasbrooking.pkg.schachfish.service.mapper.toInternalEntity
import com.nicholasbrooking.pkg.schachfish.service.move.MoveService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@Controller
class MoveController(
        private val moveFinderService: MoveFinderService,
        private val moveService: MoveService
) {

    @GetMapping("/moves/{boardId}")
    fun getLegalMovesFromBoardState(@PathVariable("boardId") boardId: Long): ResponseEntity<MoveCollectionDto> {
        try {
            val legalMoves = moveFinderService.getLegalMoves(boardId)
            return ResponseEntity.ok(legalMoves.toApiDto())
        } catch (e: SchachfishInvalidInput) {
            throw ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "failed getting board moves", e
            )
        }
    }

    @PostMapping("/moves/{boardId}")
    fun makeMove(@PathVariable("boardId") boardId: Long, @RequestBody(required = true) moveDto: MoveDto): ResponseEntity<String> {
        try {
            moveService.makeMove(moveDto.toInternalDto(), boardId)
            return ResponseEntity.ok("Success")
        } catch (e: SchachfishBoardNotFound) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Board not found")
        } catch (e: SchachfishInvalidMove) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid move")
        }
    }
}


