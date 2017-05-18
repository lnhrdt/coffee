package io.leonhardt.coffee.latte.friends

import io.leonhardt.coffee.latte.Errors
import io.leonhardt.coffee.latte.Result
import org.springframework.stereotype.Service

@Service
class FriendCreateService(
        val friendRepository: FriendRepository,
        val friendCreateRequestValidator: FriendCreateRequestValidator
) {
    fun create(request: Request): Result<Friend, Errors> {
        val validateResult = friendCreateRequestValidator.validate(request)

        return when (validateResult) {
            is Result.Success -> {
                val friend = friendRepository.save(Friend(name = request.name, coffees = emptyList()))
                return Result.Success(friend)
            }
            is Result.Failure -> Result.Failure(validateResult.errors)
        }
    }

    data class Request(val name: String)
}
