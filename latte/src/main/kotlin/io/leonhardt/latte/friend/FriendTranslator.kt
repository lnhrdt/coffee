package io.leonhardt.latte.friend

import io.leonhardt.latte.Translator
import org.springframework.stereotype.Service

@Service
class FriendTranslator : Translator<Friend, FriendEntity> {
        override fun translate(input: Friend): FriendEntity {
                return FriendEntity(
                        id = input.id,
                        name = input.name
                )
        }
}
