package io.leonhardt.coffee.latte.friends

import io.github.codebandits.results.Result
import io.github.codebandits.results.map
import io.leonhardt.coffee.latte.Errors
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FriendCreateService(val friendCreateRequestValidator: FriendCreateRequestValidator) {

    @Transactional
    fun create(friendNew: FriendNew): Result<Errors, Friend> {

        return friendCreateRequestValidator.validate(friendNew)
                .map { validFriendNew ->
                    FriendEntity.new {
                        name = validFriendNew.name
                    }
                }
                .map { it.toFriend() }
    }
}
