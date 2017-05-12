package io.leonhardt.latte.friends

import io.leonhardt.latte.Translator
import org.springframework.stereotype.Service

@Service
class FriendEntityTranslator : Translator<FriendEntity, Friend> {
    override fun translate(input: FriendEntity): Friend {
        return Friend(
                id = input.id ?: throw RuntimeException("id is required"),
                name = input.name ?: throw RuntimeException("name is required")
        )
    }
}
