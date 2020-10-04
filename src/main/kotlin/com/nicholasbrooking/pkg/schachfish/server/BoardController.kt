package com.nicholasbrooking.pkg.schachfish.server

import com.nicholasbrooking.pkg.schachfish.api.BoardApi
import com.nicholasbrooking.pkg.schachfish.api.model.BoardStateDto
import com.nicholasbrooking.pkg.schachfish.api.model.BoardId
import com.nicholasbrooking.pkg.schachfish.service.board.ActiveBoardService
import com.nicholasbrooking.pkg.schachfish.service.mapper.toApiDto
import com.nicholasbrooking.pkg.schachfish.service.mapper.toApiId
import com.nicholasbrooking.pkg.schachfish.service.mapper.toInternalEntity
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

    override fun createBoardFromState(boardStateDto: BoardStateDto?): ResponseEntity<BoardId> {
        requestReceiver.schachfishReceive {
            val id = activeBoardService.createBoard(boardStateDto.toInternalEntity())
            return ResponseEntity.ok(id.toApiId())
        }
    }

    override fun getBoard(boardId: Long): ResponseEntity<BoardStateDto> {
        requestReceiver.schachfishReceive {
            return ResponseEntity.ok(activeBoardService.getBoardState(boardId).toApiDto())
        }
    }

    override fun getAllBoardIds(): ResponseEntity<List<BoardId>> {
        requestReceiver.schachfishReceive {
            return ResponseEntity.ok(activeBoardService.getAllBoardIds().map { it.toApiId() })
        }
    }

}


