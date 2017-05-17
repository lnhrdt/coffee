package io.leonhardt.coffee.latte.friends

import io.leonhardt.coffee.latte.coffee.Coffee
import java.util.*

data class Friend(
        val id: UUID = UUID(0, 0),
        val name: String,
        val coffees: List<Coffee>
)
