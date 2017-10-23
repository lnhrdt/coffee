package io.leonhardt.coffee.latte.friends

import io.github.codebandits.results.Result
import io.github.codebandits.results.Success
import io.leonhardt.coffee.latte.Errors
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FriendGetAllService {

    @Transactional
    fun getAll(): Result<Errors, List<Friend>> {
        val friends = FriendEntity.all().map { it.toFriend() }
        return Success(friends)
    }
}
