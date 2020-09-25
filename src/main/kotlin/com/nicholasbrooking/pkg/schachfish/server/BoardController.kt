package com.nicholasbrooking.pkg.schachfish.server

import com.nicholasbrooking.pkg.schachfish.api.models.BoardStateDto
import com.nicholasbrooking.pkg.schachfish.service.board.ActiveBoardService
import com.nicholasbrooking.pkg.schachfish.service.exception.SchachfishBoardNotFound
import com.nicholasbrooking.pkg.schachfish.service.exception.SchachfishInvalidInput
import com.nicholasbrooking.pkg.schachfish.service.mapper.toInternalEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException


@Controller
class BoardController(
        val activeBoardService: ActiveBoardService
) {
    val requestReceiver = RequestReceiver()

    @DeleteMapping("/board/{boardId}")
    fun deleteBoard(@PathVariable("boardId") boardId: Long): ResponseEntity<String> {
        requestReceiver.schachfishReceive {
            activeBoardService.deleteBoard(boardId)
            return ResponseEntity.ok("Success")
        }
    }

    @PostMapping("/board/create")
    fun createBoardFromState(@RequestBody boardStateDto: BoardStateDto): ResponseEntity<Long> {
        requestReceiver.schachfishReceive {
            val id = activeBoardService.createBoard(boardStateDto.toInternalEntity())
            return ResponseEntity.ok(id)
        }
    }

}


