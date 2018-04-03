package io.leonhardt.coffee.latte.coffee

import io.leonhardt.coffee.latte.DatabaseTest
import io.leonhardt.coffee.latte.friends.FriendCreateService
import io.leonhardt.coffee.latte.friends.FriendNew
import io.leonhardt.coffee.latte.groups.GroupCreateService
import io.leonhardt.coffee.latte.groups.GroupNew
import io.leonhardt.coffee.latte.support.assertRight
import io.leonhardt.coffee.latte.support.closeToNow
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class CoffeeCreateServiceTest(
    @Autowired private val friendCreateService: FriendCreateService,
    @Autowired private val groupCreateService: GroupCreateService
) : DatabaseTest() {

    val subject: CoffeeCreateService = CoffeeCreateService()

    @Test
    fun `returns the persisted Coffee`() {
        val groupId = GroupNew(name = "Group 1").let { groupCreateService.create(it) }.assertRight().id
        val friend = friendCreateService.create(FriendNew(name = "test-friend", groupId = groupId)).assertRight()
        val coffeeNew = CoffeeNew(friendId = friend.id)

        val coffee = subject.create(coffeeNew = coffeeNew).assertRight()
        assertThat(coffee.friendId, equalTo(friend.id))
        assertThat(coffee.dateTime, closeToNow())
    }
}
