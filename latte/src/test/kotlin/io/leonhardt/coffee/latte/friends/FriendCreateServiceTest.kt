package io.leonhardt.coffee.latte.friends

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.github.codebandits.results.Failure
import io.github.codebandits.results.Success
import io.github.codebandits.results.failsAnd
import io.github.codebandits.results.succeeds
import io.github.codebandits.results.succeedsAnd
import io.leonhardt.coffee.latte.DatabaseTest
import io.leonhardt.coffee.latte.groups.GroupCreateService
import io.leonhardt.coffee.latte.groups.GroupNew
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.empty
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class FriendCreateServiceTest(
    @Autowired val groupCreateService: GroupCreateService
) : DatabaseTest() {

    val friendCreateRequestValidator: FriendCreateRequestValidator = mock()

    val subject: FriendCreateService = FriendCreateService(friendCreateRequestValidator)

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
