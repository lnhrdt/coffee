package io.leonhardt.latte

import io.leonhardt.latte.friend.Friend
import io.leonhardt.latte.friend.FriendEntity
import io.leonhardt.latte.friend.FriendRepository
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class DataCreator(val friendRepository: FriendRepository) {

    @EventListener
    fun run(event: ContextRefreshedEvent) {
        friendRepository.deleteAll()
        friendRepository.saveAll(listOf(
                Friend(name = "Ian Ornstein"),
                Friend(name = "Zachary Gershman"),
                Friend(name = "John Ryan")
        ))
    }
}
