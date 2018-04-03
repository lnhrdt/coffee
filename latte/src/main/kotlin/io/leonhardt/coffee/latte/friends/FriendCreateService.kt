package io.leonhardt.coffee.latte.friends

import arrow.core.Either
import arrow.core.flatMap
import io.leonhardt.coffee.latte.Errors
import io.leonhardt.coffee.latte.groups.GroupEntity
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Service

@Service
class FriendCreateService(val friendCreateRequestValidator: FriendCreateRequestValidator) {

    fun create(friendNew: FriendNew): Either<Errors, Friend> = transaction {
        val groupId = friendNew.groupId.toString()
        val foundGroup = GroupEntity.findById(groupId)
        when (foundGroup) {
            null -> Either.left(mapOf("groupId" to "groupId $groupId not found"))
            else -> Either.right(foundGroup)
        }
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
