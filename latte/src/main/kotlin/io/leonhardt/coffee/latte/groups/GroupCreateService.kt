package io.leonhardt.coffee.latte.groups

import arrow.core.Either
import io.github.codebandits.beak.newOrError
import io.leonhardt.coffee.latte.Errors
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Service

@Service
class GroupCreateService {

    fun create(groupNew: GroupNew): Either<Errors, Group> = transaction {
        GroupEntity.newOrError { name = groupNew.name }
            .map { it.toGroup() }
            .mapLeft { mapOf<String, String>() }
    }
}
