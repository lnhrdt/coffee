package io.leonhardt.coffee.latte.groups

import io.github.codebandits.results.Result
import io.github.codebandits.results.Success
import io.leonhardt.coffee.latte.Errors
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Service

@Service
class GroupCreateService {

    fun create(groupNew: GroupNew): Result<Errors, Group> = transaction {
        Success(GroupEntity.new { name = groupNew.name }.toGroup())
    }
}
