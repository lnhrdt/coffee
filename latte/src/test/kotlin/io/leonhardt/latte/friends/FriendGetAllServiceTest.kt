package io.leonhardt.latte.friends

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.theInstance
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
class FriendGetAllServiceTest {

    val friendRepository: FriendRepository = mock()
    val subject: FriendGetAllService = FriendGetAllService(friendRepository)

    @Test
    fun getAll() {
        val friends = listOf(Friend(name = "Mark Ducommun", coffees = emptyList()))
        whenever(friendRepository.findAll()).thenReturn(friends)

        assertThat(subject.getAll(), theInstance(friends))
    }
}
