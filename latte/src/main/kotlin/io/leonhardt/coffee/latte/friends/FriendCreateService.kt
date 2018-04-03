package io.leonhardt.coffee.latte.friends

import io.github.codebandits.results.Result
import io.github.codebandits.results.adapters.presenceAsResult
import io.github.codebandits.results.flatMap
import io.github.codebandits.results.map
import io.github.codebandits.results.mapError
import io.leonhardt.coffee.latte.Errors
import io.leonhardt.coffee.latte.groups.GroupEntity
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Service

@Service
class FriendCreateService(val friendCreateRequestValidator: FriendCreateRequestValidator) {

    fun create(friendNew: FriendNew): Result<Errors, Friend> = transaction {
        val groupId = friendNew.groupId.toString()
        GroupEntity.findById(groupId)
            .presenceAsResult()
            .mapError { mapOf("groupId" to "groupId $groupId not found") }
            .flatMap { groupEntity ->
                friendCreateRequestValidator.validate(friendNew)
                    .map { validFriendNew ->
                        FriendEntity.new {
                            name = validFriendNew.name
                            group = groupEntity
                        }
                    }
            }
            .map { it.toFriend() }
    }
}
