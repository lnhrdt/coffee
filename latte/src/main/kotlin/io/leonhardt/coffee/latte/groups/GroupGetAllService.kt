package io.leonhardt.coffee.latte.groups

import arrow.core.Either
import io.leonhardt.coffee.latte.Errors
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Service

@Service
class GroupGetAllService {

    fun getAll(): Either<Errors, List<Group>> = transaction {
        GroupEntity.all().map { it.toGroup() }.let { Either.right(it) }
    }
}
