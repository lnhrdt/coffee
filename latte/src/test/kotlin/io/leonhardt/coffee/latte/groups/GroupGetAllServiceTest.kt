package io.leonhardt.coffee.latte.groups

import io.leonhardt.coffee.latte.DatabaseTest
import io.leonhardt.coffee.latte.support.assertRight
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class GroupGetAllServiceTest(
    @Autowired private val groupCreateService: GroupCreateService
) : DatabaseTest() {

    val subject: GroupGetAllService = GroupGetAllService()

    @Test
    fun `returns the Groups`() {
        val newGroups = listOf(
            GroupNew(name = "Boulder"),
            GroupNew(name = "Denver")
        )
        newGroups.forEach { groupCreateService.create(it) }

        val groups = subject.getAll().assertRight()
        assertThat(groups, hasSize(2))
        assertThat(groups, hasItem(hasProperty("name", equalTo("Boulder"))))
        assertThat(groups, hasItem(hasProperty("name", equalTo("Denver"))))
    }
}
