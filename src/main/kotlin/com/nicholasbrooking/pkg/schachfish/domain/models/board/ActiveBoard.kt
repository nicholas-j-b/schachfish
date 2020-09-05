package com.nicholasbrooking.pkg.schachfish.domain.models.board

import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed


@RedisHash("ActiveBoards")
data class ActiveBoards (
        @Indexed val id: Long,
        var boards: List<BoardStateDto>
)
