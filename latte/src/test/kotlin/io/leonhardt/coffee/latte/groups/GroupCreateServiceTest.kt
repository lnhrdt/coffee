package io.leonhardt.coffee.latte.groups

import io.github.codebandits.results.succeedsAnd
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional

@RunWith(SpringRunner::class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
class GroupCreateServiceTest {

    val subject = GroupCreateService()

    @Test
    fun `returns the persisted Friend`() {
        val groupNew = GroupNew(name = "Group 1")

        subject.create(groupNew) succeedsAnd {
            MatcherAssert.assertThat(it.name, Matchers.equalTo("Group 1"))
        }
    }
}
