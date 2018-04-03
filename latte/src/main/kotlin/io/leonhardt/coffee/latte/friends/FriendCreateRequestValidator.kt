package io.leonhardt.coffee.latte.friends

import arrow.core.Either
import io.leonhardt.coffee.latte.Errors
import io.leonhardt.coffee.latte.Validator
import org.springframework.stereotype.Service

@Service
class FriendCreateRequestValidator : Validator<FriendNew> {

    override fun validate(data: FriendNew): Either<Errors, FriendNew> {
        val errors: MutableMap<String, String> = mutableMapOf()

        if (data.name.isEmpty()) {
            errors["name"] = "Required"
        }

        return when {
            errors.isEmpty() -> Either.right(data)
            else             -> Either.left(errors)
        }
    }
}
