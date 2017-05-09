package io.leonhardt.latte.friends

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(SpringJUnit4ClassRunner::class)
class FriendGetAllServiceTest {
    private val friendRepository = mock(FriendRepository::class.java)
    private val subject: FriendGetAllService = FriendGetAllService(friendRepository)

    @Test
    fun getAll() {
        val friends = listOf(Friend())
        Mockito.`when`(friendRepository.findAll()).thenReturn(friends)

        Assert.assertSame(friends, subject.getAll())
    }
}
