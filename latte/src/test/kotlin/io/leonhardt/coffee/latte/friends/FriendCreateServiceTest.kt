package io.leonhardt.coffee.latte.friends

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.github.codebandits.results.*
import io.leonhardt.coffee.latte.groups.GroupCreateService
import io.leonhardt.coffee.latte.groups.GroupNew
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.empty
import org.hamcrest.Matchers.equalTo
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
class FriendCreateServiceTest {

    val friendCreateRequestValidator: FriendCreateRequestValidator = mock()

    val subject: FriendCreateService = FriendCreateService(friendCreateRequestValidator)

    @Autowired
    lateinit var groupCreateService: GroupCreateService

    @Test
    fun `when validator succeeds, returns the persisted Friend`() {
        val groupId = GroupNew(name = "Group 1").let { groupCreateService.create(it) }.succeeds().id
        val friendNew = FriendNew(name = "Anna Yu", groupId = groupId)
        whenever(friendCreateRequestValidator.validate(friendNew)).thenReturn(Success(friendNew))

        subject.create(friendNew) succeedsAnd {
            assertThat(it.name, equalTo("Anna Yu"))
            assertThat(it.coffees, empty())
        }
    }

    @Test
    fun `when validator fails, returns the errors`() {
        val groupId = GroupNew(name = "Group 1").let { groupCreateService.create(it) }.succeeds().id
        val request = FriendNew(name = "Rodolfo Sanchez", groupId = groupId)
        val errors = mapOf("name" to "This name is too cool.")
        whenever(friendCreateRequestValidator.validate(request)).thenReturn(Failure(errors))

        subject.create(request) failsAnd {
            assertThat(it, equalTo(errors))
        }
    }
}
