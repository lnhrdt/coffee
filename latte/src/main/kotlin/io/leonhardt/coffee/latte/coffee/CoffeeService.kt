package io.leonhardt.coffee.latte.coffee

import io.leonhardt.coffee.latte.Errors
import io.leonhardt.coffee.latte.Result
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*

@Service
class CoffeeService(val coffeeRepository: CoffeeRepository) {
    fun create(request: Request): Result<Coffee, Errors> {
        val newCoffee = Coffee(friendId = request.friendId, dateTime = Instant.now())
        val savedCoffee = coffeeRepository.save(newCoffee)
        return Result.Success(savedCoffee)
    }

    data class Request(val friendId: UUID)
}
