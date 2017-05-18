package io.leonhardt.coffee.latte.friends

import io.leonhardt.coffee.latte.Result
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.hasEntry
import org.hamcrest.Matchers.instanceOf
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
class FriendCreateRequestValidatorTest {

    val subject = FriendCreateRequestValidator()

    @Test
    fun validate_whenFirstNameIsPresent_returnValid() {
        val result = subject.validate(FriendCreateService.Request("Angela Chin"))

        assertThat(result, instanceOf(Result.Success::class.java))
    }

    @Test
    fun validate_whenFirstNameIsEmptyString_returnInvalid() {
        val result = subject.validate(FriendCreateService.Request("")) as Result.Failure

        assertThat(result.errors, hasEntry("name", "Required"))
    }
}
