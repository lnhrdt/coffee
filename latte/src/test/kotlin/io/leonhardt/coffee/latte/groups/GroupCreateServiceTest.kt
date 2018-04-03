package io.leonhardt.coffee.latte.groups

import io.github.codebandits.results.succeedsAnd
import io.leonhardt.coffee.latte.DatabaseTest
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test

class GroupCreateServiceTest : DatabaseTest() {

    val subject = GroupCreateService()

    @Test
    fun `returns the persisted Group`() {
        val groupNew = GroupNew(name = "Group 1")

        subject.create(groupNew) succeedsAnd {
            MatcherAssert.assertThat(it.name, Matchers.equalTo("Group 1"))
        }
    }
}
