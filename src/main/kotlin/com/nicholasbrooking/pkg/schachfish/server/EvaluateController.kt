package com.nicholasbrooking.pkg.schachfish.server

import com.nicholasbrooking.pkg.schachfish.api.models.BoardStateDto
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody


@Controller
class EvaluateController {

    @PostMapping("/evaluate")
    fun getBoardEvaluation(@RequestBody boardStateDto: BoardStateDto): String {
        println(boardStateDto)
        return "this guy"
    }
}


