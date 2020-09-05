package com.nicholasbrooking.pkg.schachfish.server

import com.nicholasbrooking.pkg.schachfish.api.models.BoardStateDto
import com.nicholasbrooking.pkg.schachfish.service.board.ActiveBoardService
import com.nicholasbrooking.pkg.schachfish.service.exception.SchachfishInvalidInput
import com.nicholasbrooking.pkg.schachfish.service.mapper.toInternalEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.server.ResponseStatusException


@Controller
class BoardController (
        val activeBoardService: ActiveBoardService
){

    @PostMapping("/board/create")
    fun createBoardFromState(@RequestBody boardStateDto: BoardStateDto): ResponseEntity<Long> {
        try {
            val id = activeBoardService.createBoard(boardStateDto.toInternalEntity())
            return ResponseEntity.ok(id)
        } catch (e: SchachfishInvalidInput) {
            throw ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Boardstate did not internalise", e
            )
        }
    }

}


