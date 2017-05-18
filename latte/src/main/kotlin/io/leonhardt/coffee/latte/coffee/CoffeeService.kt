package io.leonhardt.coffee.latte.coffee

import io.leonhardt.coffee.latte.Errors
import io.leonhardt.coffee.latte.Result
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*

@Service
class CoffeeService(val coffeeRepository: CoffeeRepository) {
    fun create(createRequest: CreateRequest): Result<Unit, Errors> {
        val coffee = Coffee(friendId = createRequest.friendId, dateTime = Instant.now())
        coffeeRepository.save(coffee)
        return Result.Success(Unit)
    }

    data class CreateRequest(val friendId: UUID)
}
