package io.leonhardt.latte.friends

import com.nhaarman.mockito_kotlin.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.theInstance
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
class FriendCreateServiceTest {

    val friendRepository: FriendRepository = mock()
    val subject: FriendCreateService = FriendCreateService(friendRepository)

    @Test
    fun create() {
        val request = FriendCreateService.Request(name = "Anna Yu")
        val friend = Friend(name = request.name, coffees = emptyList())
        whenever(friendRepository.save(any())).thenReturn(friend)

        val response = subject.create(request)

        assertThat(response, theInstance(friend))
        verify(friendRepository).save(check {
            assertThat(it.name, equalTo(request.name))
        })
    }
}
