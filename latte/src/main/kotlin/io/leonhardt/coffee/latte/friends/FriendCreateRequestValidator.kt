package io.leonhardt.coffee.latte.friends

import io.github.codebandits.results.Failure
import io.github.codebandits.results.Result
import io.github.codebandits.results.Success
import io.leonhardt.coffee.latte.Errors
import io.leonhardt.coffee.latte.Validator
import org.springframework.stereotype.Service

@Service
class FriendCreateRequestValidator : Validator<FriendNew> {

    override fun validate(data: FriendNew): Result<Errors, FriendNew> {
        val errors: MutableMap<String, String> = mutableMapOf()

        if (data.name.isEmpty()) {
            errors.put("name", "Required")
        }

        return when {
            errors.isEmpty() -> Success(data)
            else -> Failure(errors)
        }
    }
}
