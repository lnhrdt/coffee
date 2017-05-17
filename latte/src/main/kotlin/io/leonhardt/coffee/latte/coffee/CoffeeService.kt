package io.leonhardt.coffee.latte.coffee

import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*

@Service
class CoffeeService(val coffeeRepository: CoffeeRepository) {
    fun create(createRequest: CreateRequest): Unit {
        val coffee = Coffee(friendId = createRequest.friendId, dateTime = Instant.now())
        coffeeRepository.save(coffee)
    }

    data class CreateRequest(val friendId: UUID)
}
