package io.leonhardt.coffee.latte.friends

import io.github.codebandits.results.Result
import io.github.codebandits.results.adapters.presenceAsResult
import io.github.codebandits.results.flatMap
import io.github.codebandits.results.map
import io.github.codebandits.results.mapError
import io.leonhardt.coffee.latte.Errors
import io.leonhardt.coffee.latte.groups.GroupEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FriendCreateService(val friendCreateRequestValidator: FriendCreateRequestValidator) {

    @Transactional
    fun create(friendNew: FriendNew): Result<Errors, Friend> {
        val groupId = friendNew.groupId.toString()
        return GroupEntity.findById(groupId)
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
