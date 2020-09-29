package com.nicholasbrooking.pkg.schachfish.server

import com.nicholasbrooking.pkg.schachfish.service.exception.SchachfishBoardNotFound
import com.nicholasbrooking.pkg.schachfish.service.exception.SchachfishInvalidInput
import com.nicholasbrooking.pkg.schachfish.service.exception.SchachfishInvalidMove
import org.springframework.data.redis.RedisConnectionFailureException
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class RequestReceiver() {
    inline fun <R> schachfishReceive(block: () -> R): R {
        try {
            return block()
        } catch (e: SchachfishBoardNotFound) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Board not found", e)
        } catch (e: SchachfishInvalidMove) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid move", e)
        } catch (e: SchachfishInvalidInput) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid input", e)
        } catch (e: RedisConnectionFailureException) {
            throw ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Redis down", e)
        }
    }
}
