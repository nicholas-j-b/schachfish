package com.nicholasbrooking.pkg.schachfish.server

import com.nicholasbrooking.pkg.schachfish.api.BoardApi
import com.nicholasbrooking.pkg.schachfish.api.model.BoardStateDto
import com.nicholasbrooking.pkg.schachfish.service.board.ActiveBoardService
import com.nicholasbrooking.pkg.schachfish.service.mapper.toApiDto
import com.nicholasbrooking.pkg.schachfish.service.mapper.toInternalDto
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller


@Controller
class BoardController(
        val activeBoardService: ActiveBoardService
): BoardApi {
    val requestReceiver = RequestReceiver()

    override fun deleteBoard(boardId: Long): ResponseEntity<String> {
        requestReceiver.schachfishReceive {
            activeBoardService.deleteBoard(boardId)
            return ResponseEntity.ok("Success")
        }
    }

    override fun createBoardFromState(boardStateDto: BoardStateDto?): ResponseEntity<Long> {
        requestReceiver.schachfishReceive {
            val id = activeBoardService.createBoard(boardStateDto.toInternalDto())
            return ResponseEntity.ok(id)
        }
    }

    override fun getBoard(boardId: Long): ResponseEntity<BoardStateDto> {
        requestReceiver.schachfishReceive {
            return ResponseEntity.ok(activeBoardService.getBoardState(boardId).toApiDto())
        }
    }

    override fun getAllBoardIds(): ResponseEntity<List<Long>> {
        requestReceiver.schachfishReceive {
            return ResponseEntity.ok(activeBoardService.getAllBoardIds().map { it })
        }
    }

}


