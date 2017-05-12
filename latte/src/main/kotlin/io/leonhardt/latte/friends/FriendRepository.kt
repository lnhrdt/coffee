package io.leonhardt.latte.friends

import io.leonhardt.latte.DataRepository
import io.leonhardt.latte.Translator
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class FriendRepository(
        dataTranslator: Translator<Friend, FriendEntity>,
        entityTranslator: Translator<FriendEntity, Friend>,
        entityRepository: JpaRepository<FriendEntity, UUID>
) : DataRepository<Friend, FriendEntity, UUID>(
        dataTranslator = dataTranslator,
        entityTranslator = entityTranslator,
        entityRepository = entityRepository
)
