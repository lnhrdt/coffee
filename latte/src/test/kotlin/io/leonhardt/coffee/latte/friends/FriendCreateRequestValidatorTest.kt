package io.leonhardt.coffee.latte.friends

import io.leonhardt.coffee.latte.support.assertLeft
import io.leonhardt.coffee.latte.support.assertRight
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.hasEntry
import org.junit.jupiter.api.Test
import java.util.*

class FriendCreateRequestValidatorTest {

    val subject = FriendCreateRequestValidator()

    @Test
    fun `when firstName is present, succeeds`() {
        val friendNew = FriendNew(name = "Angela Chin", groupId = UUID.randomUUID())
        assertThat(subject.validate(friendNew).assertRight(), equalTo(friendNew))
    }

    @Test
    fun `when firstName is is "", fails`() {
        val errors = subject.validate(FriendNew(name = "", groupId = UUID.randomUUID())).assertLeft()
        assertThat(errors, hasEntry("name", "Required"))
    }
}
