package com.nicholasbrooking.pkg.schachfish.server

import com.nicholasbrooking.pkg.schachfish.api.EvaluationApi
import com.nicholasbrooking.pkg.schachfish.api.model.BoardEvaluationDto
import com.nicholasbrooking.pkg.schachfish.api.model.BoardStateDto
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller


@Controller
class EvaluationController: EvaluationApi {
    override fun getBoardEvaluation(boardStateDto: BoardStateDto?): ResponseEntity<BoardEvaluationDto> {
        TODO("Not yet implemented")
    }
}
