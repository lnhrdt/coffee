package io.leonhardt.coffee.latte.groups

import io.github.codebandits.results.succeedsAnd
import io.leonhardt.coffee.latte.DatabaseTest
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class GroupGetAllServiceTest(
    @Autowired private val groupCreateService: GroupCreateService
) : DatabaseTest() {

    val subject: GroupGetAllService = GroupGetAllService()

    @Test
    fun `returns the Groups`() {
        val groups = listOf(
            GroupNew(name = "Boulder"),
            GroupNew(name = "Denver")
        )
        groups.forEach { groupCreateService.create(it) }

        subject.getAll() succeedsAnd {
            MatcherAssert.assertThat(it, Matchers.hasSize(2))
            MatcherAssert.assertThat(it, Matchers.hasItem(Matchers.hasProperty("name", Matchers.equalTo("Boulder"))))
            MatcherAssert.assertThat(it, Matchers.hasItem(Matchers.hasProperty("name", Matchers.equalTo("Denver"))))
        }
    }
}
