package io.leonhardt.coffee.latte.coffee

import arrow.core.Either
import arrow.core.flatMap
import io.github.codebandits.beak.findByIdOrError
import io.github.codebandits.beak.newOrError
import io.leonhardt.coffee.latte.Errors
import io.leonhardt.coffee.latte.friends.FriendEntity
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class CoffeeCreateService {

    fun create(coffeeNew: CoffeeNew): Either<Errors, Coffee> = transaction {
        val friendId = coffeeNew.friendId.toString()
        FriendEntity.findByIdOrError(friendId)
            .flatMap { friendEntity ->
                CoffeeEntity.newOrError {
                    friend = friendEntity
                    dateTime = DateTime(Instant.now().toEpochMilli())
                }
            }
            .map { it.toCoffee() }
            .mapLeft { mapOf("friendId" to "friendId $friendId not found") }
    }
}
