package io.leonhardt.coffee.latte.friends

import io.leonhardt.coffee.latte.DatabaseTest
import io.leonhardt.coffee.latte.groups.GroupCreateService
import io.leonhardt.coffee.latte.groups.GroupNew
import io.leonhardt.coffee.latte.support.assertRight
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class FriendFindAllByGroupServiceTest(
    @Autowired private val friendCreateService: FriendCreateService,
    @Autowired private val groupCreateService: GroupCreateService
) : DatabaseTest() {

    val subject = FriendFindAllByGroupService()

    @Test
    fun `returns the Friends`() {
        val groups = listOf(GroupNew(name = "Group 1"), GroupNew(name = "Group 2"))
            .map { groupCreateService.create(it).assertRight() }
        val group1 = groups[0].id
        val group2 = groups[1].id
        val friends = listOf(
            FriendNew(name = "Mark Ducommun", groupId = group1),
            FriendNew(name = "Christine Ducommun", groupId = group1),
            FriendNew(name = "Alex Thornburg", groupId = group2)
        )
        friends.forEach { friendCreateService.create(it) }

        val foundFriends = subject.findAllByGroup(group1).assertRight()
        assertThat(foundFriends, hasSize(2))
        assertThat(foundFriends, hasItem(hasProperty("name", equalTo("Mark Ducommun"))))
        assertThat(foundFriends, hasItem(hasProperty("name", equalTo("Christine Ducommun"))))
    }
}
