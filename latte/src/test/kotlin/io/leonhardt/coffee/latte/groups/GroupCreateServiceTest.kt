package io.leonhardt.coffee.latte.groups

import io.leonhardt.coffee.latte.DatabaseTest
import io.leonhardt.coffee.latte.support.assertRight
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test

class GroupCreateServiceTest : DatabaseTest() {

    val subject = GroupCreateService()

    @Test
    fun `returns the persisted Group`() {
        val groupNew = GroupNew(name = "Group 1")

        val group = subject.create(groupNew).assertRight()
        assertThat(group.name, equalTo("Group 1"))
    }
}
