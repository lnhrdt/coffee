package io.leonhardt.coffee.latte.groups

import io.github.codebandits.results.Result
import io.github.codebandits.results.Success
import io.leonhardt.coffee.latte.Errors
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GroupGetAllService {

    @Transactional
    fun getAll(): Result<Errors, List<Group>> {
        val groups = GroupEntity.all().map { it.toGroup() }
        return Success(groups)
    }
}
