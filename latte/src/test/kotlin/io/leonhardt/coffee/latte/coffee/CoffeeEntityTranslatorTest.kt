package io.leonhardt.coffee.latte.coffee

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner
import java.time.Instant
import java.util.*

@RunWith(SpringRunner::class)
class CoffeeEntityTranslatorTest {
    val subject: CoffeeEntityTranslator = CoffeeEntityTranslator()
    val validCoffeeEntity = CoffeeEntity(id = UUID.randomUUID(), friendId = UUID.randomUUID(), dateTime = Instant.now())

    @Test
    fun translate() {
        val input = validCoffeeEntity

        val output = subject.translate(input)

        assertThat(output.id, equalTo(input.id))
        assertThat(output.friendId, equalTo(input.friendId))
        assertThat(output.dateTime, equalTo(input.dateTime))
    }

    @Test(expected = RuntimeException::class)
    fun translate_missingId() {
        val input = validCoffeeEntity
        input.id = null

        subject.translate(input)
    }

    @Test(expected = RuntimeException::class)
    fun translate_missingFriendId() {
        val input = validCoffeeEntity
        input.friendId = null

        subject.translate(input)
    }

    @Test(expected = RuntimeException::class)
    fun translate_missingDateTime() {
        val input = validCoffeeEntity
        input.dateTime = null

        subject.translate(input)
    }
}
