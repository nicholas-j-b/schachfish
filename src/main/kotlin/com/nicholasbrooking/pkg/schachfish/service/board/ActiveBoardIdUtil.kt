package com.nicholasbrooking.pkg.schachfish.service.board

object ActiveBoardIdUtil {
    private var id = (0..9223372036854775807).random()

    fun getNextId(): Long {
        return id++
    }
}