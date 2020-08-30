package com.nicholasbrooking.pkg.schachfish.server

import com.nicholasbrooking.pkg.schachfish.api.models.BoardStateDto
import com.nicholasbrooking.pkg.schachfish.api.models.MoveCollectionDto
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody


@Controller
class EvaluateController {

    @PostMapping("/evaluate")
    fun getBoardEvaluation(@RequestBody boardStateDto: BoardStateDto): ResponseEntity<Double> {
        println(boardStateDto)
        return ResponseEntity.ok(0.0)
    }
}


