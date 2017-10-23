package io.leonhardt.coffee.latte.friends

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.github.codebandits.results.Failure
import io.github.codebandits.results.Success
import io.github.codebandits.results.failsAnd
import io.github.codebandits.results.succeedsAnd
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.empty
import org.hamcrest.Matchers.equalTo
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
class FriendCreateServiceTest {

    val friendCreateRequestValidator: FriendCreateRequestValidator = mock()

    val subject: FriendCreateService = FriendCreateService(friendCreateRequestValidator)

    @Test
    fun `when validator succeeds, returns the persisted Friend`() {
        val friendNew = FriendNew(name = "Anna Yu")
        whenever(friendCreateRequestValidator.validate(friendNew)).thenReturn(Success(friendNew))

        subject.create(friendNew) succeedsAnd {
            assertThat(it.name, equalTo("Anna Yu"))
            assertThat(it.coffees, empty())
        }
    }

    @Test
    fun `when validator fails, returns the errors`() {
        val request = FriendNew(name = "Rodolfo Sanchez")
        val errors = mapOf("name" to "This name is too cool.")
        whenever(friendCreateRequestValidator.validate(request)).thenReturn(Failure(errors))

        subject.create(request) failsAnd {
            assertThat(it, equalTo(errors))
        }
    }
}
