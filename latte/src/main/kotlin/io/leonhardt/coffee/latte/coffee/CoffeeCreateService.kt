package io.leonhardt.coffee.latte.coffee

import io.github.codebandits.results.Result
import io.github.codebandits.results.adapters.presenceAsResult
import io.github.codebandits.results.map
import io.github.codebandits.results.mapError
import io.leonhardt.coffee.latte.Errors
import io.leonhardt.coffee.latte.friends.FriendEntity
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class CoffeeCreateService {

    fun create(coffeeNew: CoffeeNew): Result<Errors, Coffee> = transaction {
        val friendId = coffeeNew.friendId.toString()
        FriendEntity.findById(friendId)
            .presenceAsResult()
            .mapError { mapOf("friendId" to "friendId $friendId not found") }
            .map { friendEntity ->
                CoffeeEntity.new {
                    friend = friendEntity
                    dateTime = DateTime(Instant.now().toEpochMilli())
                }
            }
            .map { it.toCoffee() }
    }
}
