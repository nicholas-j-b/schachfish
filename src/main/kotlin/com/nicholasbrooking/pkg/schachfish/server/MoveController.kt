package com.nicholasbrooking.pkg.schachfish.server

import com.nicholasbrooking.pkg.schachfish.api.MoveApi
import com.nicholasbrooking.pkg.schachfish.api.model.MoveCollectionDto
import com.nicholasbrooking.pkg.schachfish.api.model.MoveDto
import com.nicholasbrooking.pkg.schachfish.service.move.MoveFinderService
import com.nicholasbrooking.pkg.schachfish.service.mapper.toApiDto
import com.nicholasbrooking.pkg.schachfish.service.mapper.toInternalDto
import com.nicholasbrooking.pkg.schachfish.service.mapper.toInternalEntity
import com.nicholasbrooking.pkg.schachfish.service.move.MoveService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller

@Controller
class MoveController(
        private val moveFinderService: MoveFinderService,
        private val moveService: MoveService
): MoveApi {
    val requestReceiver = RequestReceiver()
    override fun getLegalMovesFromBoardId(boardId: Long): ResponseEntity<MoveCollectionDto> {
        requestReceiver.schachfishReceive {
            val legalMoves = moveFinderService.getLegalMoves(boardId)
            return ResponseEntity.ok(legalMoves.toApiDto())
        }
    }

    override fun makeMove(boardId: Long, moveDto: MoveDto?): ResponseEntity<String> {
        requestReceiver.schachfishReceive {
            moveService.makeMove(moveDto.toInternalDto(), boardId)
            return ResponseEntity.ok("Success")
        }
    }
}


