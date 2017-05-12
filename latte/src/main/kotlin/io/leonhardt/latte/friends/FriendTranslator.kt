package io.leonhardt.latte.friends

import io.leonhardt.latte.Translator
import io.leonhardt.latte.coffee.CoffeeTranslator
import org.springframework.stereotype.Service

@Service
class FriendTranslator(val coffeeTranslator: CoffeeTranslator) : Translator<Friend, FriendEntity> {
    override fun translate(input: Friend): FriendEntity {
        return FriendEntity(
                id = input.id,
                name = input.name,
                coffees = input.coffees.map { coffeeTranslator.translate(it) }
        )
    }
}
