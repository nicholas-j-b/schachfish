package com.nicholasbrooking.pkg.schachfish.domain.models.board

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash


@RedisHash("ActiveBoards")
data class ActiveBoard (
        @Id val id: Long?,
        var currentState: String?
)