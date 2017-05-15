package io.leonhardt.latte.friends

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.leonhardt.latte.coffee.Coffee
import io.leonhardt.latte.coffee.CoffeeEntity
import io.leonhardt.latte.coffee.CoffeeTranslator
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.hamcrest.Matchers.equalTo
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner
import java.time.Instant
import java.util.*

@RunWith(SpringRunner::class)
class FriendTranslatorTest {

    val coffeeTranslator: CoffeeTranslator = mock()
    val subject = FriendTranslator(coffeeTranslator)

    @Test
    fun translate() {
        val coffee1: Coffee = Coffee(id = UUID.randomUUID(), friendId = UUID.randomUUID(), dateTime = Instant.now())
        val coffee2: Coffee = Coffee(id = UUID.randomUUID(), friendId = UUID.randomUUID(), dateTime = Instant.now())
        val coffeeEntity1: CoffeeEntity = CoffeeEntity(id = UUID.randomUUID())
        val coffeeEntity2: CoffeeEntity = CoffeeEntity(id = UUID.randomUUID())
        whenever(coffeeTranslator.translate(coffee1)).thenReturn(coffeeEntity1)
        whenever(coffeeTranslator.translate(coffee2)).thenReturn(coffeeEntity2)
        val input = Friend(id = UUID.randomUUID(), name = "friend", coffees = listOf(coffee1, coffee2))

        val output = subject.translate(input)

        assertThat(output.id, equalTo(input.id))
        assertThat(output.name, equalTo(input.name))
        assertThat(output.coffees, contains(coffeeEntity1, coffeeEntity2))
    }
}
