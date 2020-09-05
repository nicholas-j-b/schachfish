package com.nicholasbrooking.pkg.schachfish.repositories

import com.nicholasbrooking.pkg.schachfish.domain.models.board.ActiveBoard
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ActiveBoardRepository : CrudRepository<ActiveBoard, Long> {

}