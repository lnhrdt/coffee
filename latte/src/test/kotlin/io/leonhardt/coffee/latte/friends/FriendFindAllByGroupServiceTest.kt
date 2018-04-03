package io.leonhardt.coffee.latte.friends

import io.github.codebandits.results.succeeds
import io.github.codebandits.results.succeedsAnd
import io.github.codebandits.results.traverse
import io.leonhardt.coffee.latte.DatabaseTest
import io.leonhardt.coffee.latte.groups.GroupCreateService
import io.leonhardt.coffee.latte.groups.GroupNew
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
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
                .map { groupCreateService.create(it) }
                .traverse()
                .succeeds()
        val group1 = groups[0].id
        val group2 = groups[1].id
        val friends = listOf(
                FriendNew(name = "Mark Ducommun", groupId = group1),
                FriendNew(name = "Christine Ducommun", groupId = group1),
                FriendNew(name = "Alex Thornburg", groupId = group2)
        )
        friends.forEach { friendCreateService.create(it) }

        subject.findAllByGroup(group1) succeedsAnd {
            MatcherAssert.assertThat(it, Matchers.hasSize(2))
            MatcherAssert.assertThat(it, Matchers.hasItem(Matchers.hasProperty("name", Matchers.equalTo("Mark Ducommun"))))
            MatcherAssert.assertThat(it, Matchers.hasItem(Matchers.hasProperty("name", Matchers.equalTo("Christine Ducommun"))))
        }
    }
}
