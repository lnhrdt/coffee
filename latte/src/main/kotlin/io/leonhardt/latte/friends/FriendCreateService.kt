package io.leonhardt.latte.friends

import org.springframework.stereotype.Service

@Service
class FriendCreateService(
        val friendRepository: FriendRepository,
        val friendCreateRequestValidator: FriendCreateRequestValidator
) {
    fun create(request: Request): Response {
        val errors = friendCreateRequestValidator.validate(request)

        if (errors.isNotEmpty()) {
            return Response(errors = errors)
        }

        val friend = friendRepository.save(Friend(name = request.name, coffees = emptyList()))
        return Response(friend = friend)
    }

    data class Request(val name: String)
    data class Response(
            val friend: Friend? = null,
            val errors: Map<String, String> = emptyMap()
    )
}
