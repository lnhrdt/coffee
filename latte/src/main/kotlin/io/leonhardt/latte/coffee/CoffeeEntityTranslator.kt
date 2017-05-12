package io.leonhardt.latte.coffee

import io.leonhardt.latte.Translator
import org.springframework.stereotype.Service

@Service
class CoffeeEntityTranslator : Translator<CoffeeEntity, Coffee> {
    override fun translate(input: CoffeeEntity): Coffee {
        return Coffee(
                id = input.id ?: throw RuntimeException("id is required"),
                friendId = input.friendId ?: throw RuntimeException("friendId is required"),
                dateTime = input.dateTime ?: throw RuntimeException("dateTime is required")
        )
    }
}
