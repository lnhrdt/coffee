package io.leonhardt.coffee.latte.friends

import io.leonhardt.coffee.latte.Translator
import io.leonhardt.coffee.latte.coffee.CoffeeEntityTranslator
import org.springframework.stereotype.Service

@Service
class FriendEntityTranslator(val coffeeEntityTranslator: CoffeeEntityTranslator) : Translator<FriendEntity, Friend> {
    override fun translate(input: FriendEntity): Friend {
        return Friend(
                id = input.id ?: throw RuntimeException("id is required"),
                name = input.name ?: throw RuntimeException("name is required"),
                coffees = input.coffees.map { coffeeEntityTranslator.translate(it) }
        )
    }
}
