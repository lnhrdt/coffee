package io.leonhardt.coffee.latte.friends

import io.github.codebandits.results.Result
import io.github.codebandits.results.Success
import io.leonhardt.coffee.latte.Errors
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Service
import java.util.*

@Service
class FriendFindAllByGroupService {

    fun findAllByGroup(groupId: UUID): Result<Errors, List<Friend>> = transaction {
        FriendEntity.find { FriendTable.group eq groupId.toString() }.map { it.toFriend() }.let { Success(it) }
    }
}
