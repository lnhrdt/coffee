package io.leonhardt.coffee.latte.friends

import io.github.codebandits.results.succeedsAnd
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
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
class FriendGetAllServiceTest {

    @Autowired
    lateinit var friendCreateService: FriendCreateService

    val subject: FriendGetAllService = FriendGetAllService()

    @Test
    fun `returns the Friends`() {
        val friends = listOf(
                FriendNew(name = "Mark Ducommun"),
                FriendNew(name = "Christine Ducommun")
        )
        friends.forEach { friendCreateService.create(it) }

        subject.getAll() succeedsAnd {
            assertThat(it, hasSize(2))
            assertThat(it, hasItem(hasProperty("name", equalTo("Mark Ducommun"))))
            assertThat(it, hasItem(hasProperty("name", equalTo("Christine Ducommun"))))
        }
    }
}
