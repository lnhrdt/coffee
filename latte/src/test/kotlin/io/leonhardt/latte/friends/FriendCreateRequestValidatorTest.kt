package io.leonhardt.latte.friends

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
class FriendCreateRequestValidatorTest {

    val subject = FriendCreateRequestValidator()

    @Test
    fun validate_whenFirstNameIsPresent_returnValid() {
        val validation: Map<String, String> = subject.validate(FriendCreateService.Request("Angela Chin"))

        assertThat(validation.isEmpty(), equalTo(true))
    }

    @Test
    fun validate_whenFirstNameIsEmptyString_returnInvalid() {
        val validation = subject.validate(FriendCreateService.Request(""))

        assertThat(validation, hasEntry("name", "Required"))
    }
}
