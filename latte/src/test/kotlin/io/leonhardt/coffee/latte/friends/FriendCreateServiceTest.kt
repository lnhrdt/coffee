package io.leonhardt.coffee.latte.friends

import com.nhaarman.mockito_kotlin.*
import io.leonhardt.coffee.latte.Errors
import io.leonhardt.coffee.latte.Result
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

@RunWith(SpringRunner::class)
class FriendCreateServiceTest {

    val friendRepository: FriendRepository = mock()
    val friendCreateRequestValidator: FriendCreateRequestValidator = mock()
    val subject: FriendCreateService = FriendCreateService(friendRepository, friendCreateRequestValidator)

    @Test
    fun create_whenResultIsSuccess() {
        val request = FriendCreateService.Request(name = "Anna Yu")
        val savedFriend = Friend(id = UUID.randomUUID(), name = request.name, coffees = emptyList())
        whenever(friendCreateRequestValidator.validate(request)).thenReturn(Result.Success(Unit))
        whenever(friendRepository.save(any())).thenReturn(savedFriend)

        val success = subject.create(request) as Result.Success

        assertThat(success.data, theInstance(savedFriend))
        verify(friendRepository).save(check {
            assertThat(it.name, equalTo(request.name))
            assertThat(it.coffees, empty())
        })
    }

    @Test
    fun create_whenResultIsFailure() {
        val request = FriendCreateService.Request(name = "Rodolfo Sanchez")
        val errors = mapOf("name" to "This name is too cool.")
        whenever(friendCreateRequestValidator.validate(request)).thenReturn(Result.Failure(errors))

        val failure = subject.create(request) as Result.Failure

        assertThat(failure.errors, theInstance(errors))
        verify(friendRepository, never()).save(any())
    }
}
