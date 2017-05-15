package io.leonhardt.latte.friends

import org.springframework.stereotype.Service

@Service
class FriendCreateService(val friendRepository: FriendRepository) {
    fun create(request: Request): Friend = friendRepository.save(Friend(name = request.name, coffees = emptyList()))

    data class Request(val name: String)
}
