package io.leonhardt.latte.coffee

import io.leonhardt.latte.Translator
import org.springframework.stereotype.Service

@Service
class CoffeeTranslator : Translator<Coffee, CoffeeEntity> {
    override fun translate(input: Coffee): CoffeeEntity {
        return CoffeeEntity(
                id = input.id,
                friendId = input.friendId,
                dateTime = input.dateTime
        )
    }
}
