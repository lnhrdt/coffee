package io.leonhardt.latte.coffee

import com.nhaarman.mockito_kotlin.check
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.leonhardt.latte.support.closeToNow
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

@RunWith(SpringRunner::class)
class CoffeeServiceTest {

    val coffeeRepository: CoffeeRepository = mock()
    val subject: CoffeeService = CoffeeService(coffeeRepository)

    @Test
    fun create() {
        val givenFriendId = UUID.randomUUID()
        val createRequest = CoffeeService.CreateRequest(friendId = givenFriendId)

        subject.create(createRequest = createRequest)

        verify(coffeeRepository).save(check {
            assertThat(it.friendId, equalTo(givenFriendId))
            assertThat(it.dateTime, closeToNow())
        })
    }
}
