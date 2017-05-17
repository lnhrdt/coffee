package io.leonhardt.coffee.latte.friends

import org.springframework.stereotype.Service

@Service
class FriendGetAllService(val friendRepository: FriendRepository) {
    fun getAll(): List<Friend> = friendRepository.findAll()
}
