package io.leonhardt.coffee.latte.coffee

import io.github.codebandits.results.Result
import io.github.codebandits.results.adapters.presenceAsResult
import io.github.codebandits.results.map
import io.github.codebandits.results.mapError
import io.leonhardt.coffee.latte.Errors
import io.leonhardt.coffee.latte.friends.FriendEntity
import org.joda.time.DateTime
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Service
class CoffeeCreateService {

    @Transactional
    fun create(coffeeNew: CoffeeNew): Result<Errors, Coffee> {
        val friendId = coffeeNew.friendId.toString()
        return FriendEntity.findById(friendId)
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
