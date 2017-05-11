package io.leonhardt.latte.friend

import io.leonhardt.latte.AutomaticRepository
import io.leonhardt.latte.Translator
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class FriendRepository(
        requestTranslator: Translator<Friend, FriendEntity>,
        repository: JpaRepository<FriendEntity, UUID>,
        responseTranslator: Translator<FriendEntity, Friend>
) : AutomaticRepository<Friend, FriendEntity, UUID>(
        requestTranslator = requestTranslator,
        repository = repository,
        responseTranslator = responseTranslator
)
