package io.leonhardt.coffee.latte.groups

import io.github.codebandits.results.Result
import io.github.codebandits.results.Success
import io.leonhardt.coffee.latte.Errors
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Service

@Service
class GroupGetAllService {

    fun getAll(): Result<Errors, List<Group>> = transaction {
        GroupEntity.all().map { it.toGroup() }.let { Success(it) }
    }
}
