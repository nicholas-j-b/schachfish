package com.nicholasbrooking.pkg.schachfish.service.board

object ActiveBoardIdUtil {
    private var id = 0L

    fun getNextId(): Long {
        return id++
    }
}