package io.leonhardt.latte.support

import org.hamcrest.Description
import org.hamcrest.TypeSafeDiagnosingMatcher
import java.time.Duration

import java.time.Instant

class IsCloseToNow(val delta: Duration) : TypeSafeDiagnosingMatcher<Instant>() {

    val now: Instant = Instant.now()

    override fun matchesSafely(item: Instant, mismatchDescription: Description): Boolean {
        val difference = Duration.between(now, item)

        if (difference.abs() > delta) {
            mismatchDescription
                    .appendValue(item)
                    .appendText(" was more than ")
                    .appendValue(delta)
                    .appendText(" from ")
                    .appendValue(now)
            return false
        }

        return true
    }

    override fun describeTo(description: Description) {
        description.appendText("an Instant within ")
                .appendValue(delta)
                .appendText(" of ")
                .appendValue(now)
    }
}

fun closeToNow(delta: Duration = Duration.ofSeconds(1)) = IsCloseToNow(delta)
