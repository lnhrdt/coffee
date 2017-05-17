package io.leonhardt.latte.friends

import com.nhaarman.mockito_kotlin.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
class FriendCreateServiceTest {

    val friendRepository: FriendRepository = mock()
    val friendCreateRequestValidator: FriendCreateRequestValidator = mock()
    val subject: FriendCreateService = FriendCreateService(friendRepository, friendCreateRequestValidator)

    @Test
    fun create_whenRequestIsValid() {
        val request = FriendCreateService.Request(name = "Anna Yu")
        val friend = Friend(name = request.name, coffees = emptyList())
        whenever(friendCreateRequestValidator.validate(request)).thenReturn(emptyMap())
        whenever(friendRepository.save(any())).thenReturn(friend)

        val response = subject.create(request)

        assertThat(response.friend, theInstance(friend))
        verify(friendRepository).save(check {
            assertThat(it.name, equalTo(request.name))
        })
    }

    @Test
    fun create_whenRequestIsNotValid() {
        val request = FriendCreateService.Request(name = "Rodolfo Sanchez")
        whenever(friendCreateRequestValidator.validate(request)).thenReturn(mapOf("name" to "This name is too cool."))

        val response = subject.create(request)

        assertThat(response.errors, hasEntry("name", "This name is too cool."))
    }
}
