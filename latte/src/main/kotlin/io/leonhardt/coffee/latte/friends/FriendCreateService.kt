package io.leonhardt.coffee.latte.friends

import arrow.core.Either
import arrow.core.flatMap
import io.github.codebandits.beak.findByIdOrError
import io.github.codebandits.beak.newOrError
import io.leonhardt.coffee.latte.Errors
import io.leonhardt.coffee.latte.groups.GroupEntity
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Service

@Service
class FriendCreateService(val friendCreateRequestValidator: FriendCreateRequestValidator) {

    fun create(friendNew: FriendNew): Either<Errors, Friend> = transaction {
        val groupId = friendNew.groupId.toString()

        friendCreateRequestValidator.validate(friendNew)
            .flatMap { validFriendNew ->
                GroupEntity.findByIdOrError(groupId)
                    .flatMap { groupEntity ->
                        FriendEntity.newOrError {
                            name = validFriendNew.name
                            group = groupEntity
                        }
                    }
                    .mapLeft { mapOf("groupId" to "groupId $groupId not found") }
            }
            .map { it.toFriend() }
    }
}
