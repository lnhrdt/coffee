package io.leonhardt.coffee.latte.groups

import io.github.codebandits.results.succeedsAnd
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional

@RunWith(SpringRunner::class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
class GroupGetAllServiceTest {

    @Autowired
    lateinit var groupCreateService: GroupCreateService

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
