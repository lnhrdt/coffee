package io.leonhardt.coffee.latte.friends

import io.leonhardt.coffee.latte.Errors
import io.leonhardt.coffee.latte.Result
import io.leonhardt.coffee.latte.Validator
import org.springframework.stereotype.Service

@Service
class FriendCreateRequestValidator : Validator<FriendCreateService.Request> {
    override fun validate(input: FriendCreateService.Request): Result<Unit, Errors> {
        val errors: MutableMap<String, String> = mutableMapOf()

        if (input.name.isEmpty()) {
            errors.put("name", "Required")
        }

        return when {
            errors.isEmpty() -> Result.Success(Unit)
            else -> Result.Failure(errors)
        }
    }
}
