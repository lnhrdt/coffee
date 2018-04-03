package io.leonhardt.coffee.latte.friends

import io.github.codebandits.results.failsAnd
import io.github.codebandits.results.succeedsWith
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.hasEntry
import org.junit.jupiter.api.Test
import java.util.*

class FriendCreateRequestValidatorTest {

    val subject = FriendCreateRequestValidator()

    @Test
    fun `when firstName is present, succeeds`() {
        val friendNew = FriendNew(name = "Angela Chin", groupId = UUID.randomUUID())
        subject.validate(friendNew) succeedsWith friendNew
    }

    @Test
    fun `when firstName is is "", fails`() {
        subject.validate(FriendNew(name = "", groupId = UUID.randomUUID())) failsAnd { errors ->
            assertThat(errors, hasEntry("name", "Required"))
        }
    }
}
