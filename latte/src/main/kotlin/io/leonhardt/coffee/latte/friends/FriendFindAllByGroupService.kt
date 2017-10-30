package io.leonhardt.coffee.latte.friends

import io.github.codebandits.results.Result
import io.github.codebandits.results.Success
import io.leonhardt.coffee.latte.Errors
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class FriendFindAllByGroupService {

    @Transactional
    fun findAllByGroup(groupId: UUID): Result<Errors, List<Friend>> {
        val friends = FriendEntity.find { FriendTable.group eq groupId.toString() }.map { it.toFriend() }
        return Success(friends)
    }
}
