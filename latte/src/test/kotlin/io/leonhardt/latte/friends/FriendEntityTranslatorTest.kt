package io.leonhardt.latte.friends

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.leonhardt.latte.coffee.Coffee
import io.leonhardt.latte.coffee.CoffeeEntity
import io.leonhardt.latte.coffee.CoffeeEntityTranslator
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.hamcrest.Matchers.equalTo
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner
import java.time.Instant
import java.util.*

@RunWith(SpringRunner::class)
class FriendEntityTranslatorTest {
    val coffeeEntityTranslator: CoffeeEntityTranslator = mock()
    val subject = FriendEntityTranslator(coffeeEntityTranslator)

    val coffeeEntity1: CoffeeEntity = CoffeeEntity(id = UUID.randomUUID())
    val coffeeEntity2: CoffeeEntity = CoffeeEntity(id = UUID.randomUUID())
    val validInput = FriendEntity(id = UUID.randomUUID(), name = "friend", coffees = listOf(coffeeEntity1, coffeeEntity2))

    @Test
    fun translate() {
        val coffee1: Coffee = Coffee(id = UUID.randomUUID(), friendId = UUID.randomUUID(), dateTime = Instant.now())
        val coffee2: Coffee = Coffee(id = UUID.randomUUID(), friendId = UUID.randomUUID(), dateTime = Instant.now())
        whenever(coffeeEntityTranslator.translate(coffeeEntity1)).thenReturn(coffee1)
        whenever(coffeeEntityTranslator.translate(coffeeEntity2)).thenReturn(coffee2)
        val input = validInput

        val output = subject.translate(input)

        assertThat(output.id, equalTo(input.id))
        assertThat(output.name, equalTo(input.name))
        assertThat(output.coffees, contains(coffee1, coffee2))
    }

    @Test(expected = RuntimeException::class)
    fun translate_missingId() {
        val input = validInput
        input.id = null

        subject.translate(input)
    }

    @Test(expected = RuntimeException::class)
    fun translate_missingName() {
        val input = validInput
        input.name = null

        subject.translate(input)
    }
}
