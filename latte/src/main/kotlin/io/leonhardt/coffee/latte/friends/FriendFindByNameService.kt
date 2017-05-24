package io.leonhardt.coffee.latte.friends

import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.stereotype.Service

@Service
class FriendFindByNameService(val friendRepository: FriendRepository) {
    fun findByName(name: String) = friendRepository.findOne(Example.of(
            Friend(name = name, coffees = emptyList()),
            ExampleMatcher.matching().withIgnorePaths("id")
    ))
}
