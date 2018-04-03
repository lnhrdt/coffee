package io.leonhardt.coffee.latte.coffee

import arrow.core.Either
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
        val foundFriend = FriendEntity.findById(friendId)
        when (foundFriend) {
            null -> Either.left(mapOf("friendId" to "friendId $friendId not found"))
            else -> Either.right(foundFriend)
        }
            .map { friendEntity ->
                CoffeeEntity.new {
                    friend = friendEntity
                    dateTime = DateTime(Instant.now().toEpochMilli())
                }
            }
            .map { it.toCoffee() }
    }
}
