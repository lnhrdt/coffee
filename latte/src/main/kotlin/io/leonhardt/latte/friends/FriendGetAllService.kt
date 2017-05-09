package io.leonhardt.latte.friends

import org.springframework.stereotype.Service

@Service
class FriendGetAllService(val friendRepository: FriendRepository) {
    fun getAll(): Iterable<Friend> = friendRepository.findAll()
}
