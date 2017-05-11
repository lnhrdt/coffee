package io.leonhardt.latte.friend

import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.stereotype.Service

@Service
class FriendGetAllService(val friendRepository: FriendRepository) {
    fun getAll(): Iterable<Friend> = friendRepository.findAll()

    fun findByName() {
        friendRepository.findOne(Example.of(Friend(name = "Jim"), ExampleMatcher.matching().withIgnorePaths("id")))
    }
}
