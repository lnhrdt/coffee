package io.leonhardt.latte.friends

import io.leonhardt.latte.DataRepository
import io.leonhardt.latte.Translator
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class FriendRepository(
        requestTranslator: Translator<Friend, FriendEntity>,
        repository: JpaRepository<FriendEntity, UUID>,
        responseTranslator: Translator<FriendEntity, Friend>
) : DataRepository<Friend, FriendEntity, UUID>(
        dataTranslator = requestTranslator,
        repository = repository,
        entityTranslator = responseTranslator
)
