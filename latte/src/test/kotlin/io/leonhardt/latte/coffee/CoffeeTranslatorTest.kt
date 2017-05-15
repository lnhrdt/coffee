package io.leonhardt.latte.coffee

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner
import java.time.Instant
import java.util.*

@RunWith(SpringRunner::class)
class CoffeeTranslatorTest {

    val subject: CoffeeTranslator = CoffeeTranslator()

    @Test
    fun translate() {
        val input = Coffee(id = UUID.randomUUID(), friendId = UUID.randomUUID(), dateTime = Instant.now())

        val output = subject.translate(input)

        assertThat(output.id, equalTo(input.id))
        assertThat(output.friendId, equalTo(input.friendId))
        assertThat(output.dateTime, equalTo(input.dateTime))
    }
}
