package io.leonhardt.coffee.latte.friends

import arrow.core.Either
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.leonhardt.coffee.latte.DatabaseTest
import io.leonhardt.coffee.latte.groups.GroupCreateService
import io.leonhardt.coffee.latte.groups.GroupNew
import io.leonhardt.coffee.latte.support.assertLeft
import io.leonhardt.coffee.latte.support.assertRight
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.empty
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class FriendCreateServiceTest(
    @Autowired val groupCreateService: GroupCreateService
) : DatabaseTest() {

    val friendCreateRequestValidator: FriendCreateRequestValidator = mock()

    val subject: FriendCreateService = FriendCreateService(friendCreateRequestValidator)

    @Test
    fun `when validator succeeds, returns the persisted Friend`() {
        val groupId = GroupNew(name = "Group 1").let { groupCreateService.create(it) }.assertRight().id
        val friendNew = FriendNew(name = "Anna Yu", groupId = groupId)
        whenever(friendCreateRequestValidator.validate(friendNew)).thenReturn(Either.right(friendNew))

        val createdFriend = subject.create(friendNew).assertRight()
        assertThat(createdFriend.name, equalTo("Anna Yu"))
        assertThat(createdFriend.coffees, empty())
    }

    @Test
    fun `when validator fails, returns the errors`() {
        val groupId = GroupNew(name = "Group 1").let { groupCreateService.create(it) }.assertRight().id
        val request = FriendNew(name = "Rodolfo Sanchez", groupId = groupId)
        val errors = mapOf("name" to "This name is too cool.")
        whenever(friendCreateRequestValidator.validate(request)).thenReturn(Either.left(errors))

        val returnedErrors = subject.create(request).assertLeft()
        assertThat(returnedErrors, equalTo(errors))
    }
}
