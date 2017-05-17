package io.leonhardt.coffee.latte.friends

import io.leonhardt.coffee.latte.Validator
import org.springframework.stereotype.Service

@Service
class FriendCreateRequestValidator : Validator<FriendCreateService.Request> {
    override fun validate(input: FriendCreateService.Request): Map<String, String> {
        val errors: MutableMap<String, String> = mutableMapOf()
        if (input.name.isEmpty()) {
            errors.put("name", "Required")
        }
        return errors
    }
}
