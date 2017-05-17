package io.leonhardt.coffee.latte.friends

import io.leonhardt.coffee.latte.Translator
import io.leonhardt.coffee.latte.coffee.CoffeeTranslator
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
