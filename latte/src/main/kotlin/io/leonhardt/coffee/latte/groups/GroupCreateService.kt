package io.leonhardt.coffee.latte.groups

import arrow.core.Either
import io.leonhardt.coffee.latte.Errors
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Service

@Service
class GroupCreateService {

    fun create(groupNew: GroupNew): Either<Errors, Group> = transaction {
        GroupEntity.new { name = groupNew.name }.toGroup().let { Either.right(it) }
    }
}
