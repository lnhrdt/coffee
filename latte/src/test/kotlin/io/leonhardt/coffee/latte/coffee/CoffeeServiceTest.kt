package io.leonhardt.coffee.latte.coffee

import com.nhaarman.mockito_kotlin.*
import io.leonhardt.coffee.latte.Result
import io.leonhardt.coffee.latte.support.closeToNow
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.theInstance
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner
import java.time.Instant
import java.util.*

@RunWith(SpringRunner::class)
class CoffeeServiceTest {

    val coffeeRepository: CoffeeRepository = mock()
    val subject: CoffeeService = CoffeeService(coffeeRepository)

    @Test
    fun create() {
        val request = CoffeeService.Request(friendId = UUID.randomUUID())
        val savedCoffee = Coffee(id = UUID.randomUUID(), friendId = request.friendId, dateTime = Instant.now())
        whenever(coffeeRepository.save(any())).thenReturn(savedCoffee)

        val result = subject.create(request = request) as Result.Success

        assertThat(result.data, theInstance(savedCoffee))
        verify(coffeeRepository).save(check {
            assertThat(it.friendId, equalTo(request.friendId))
            assertThat(it.dateTime, closeToNow())
        })
    }
}
