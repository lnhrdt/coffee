package io.leonhardt.latte.coffee

import java.time.Instant
import java.util.*

data class Coffee(
        val id: UUID = UUID(0, 0),
        val friendId: UUID,
        val dateTime: Instant
)
