package io.leonhardt.coffee.latte.groups

import io.github.codebandits.results.Result
import io.github.codebandits.results.Success
import io.leonhardt.coffee.latte.Errors
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GroupCreateService {

    @Transactional
    fun create(groupNew: GroupNew): Result<Errors, Group> {
        return Success(GroupEntity.new {
            name = groupNew.name
        }.toGroup())
    }
}
