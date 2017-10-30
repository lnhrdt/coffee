package io.leonhardt.coffee.latte.coffee

import io.github.codebandits.results.succeeds
import io.github.codebandits.results.succeedsAnd
import io.github.codebandits.results.traverse
import io.leonhardt.coffee.latte.friends.FriendCreateService
import io.leonhardt.coffee.latte.friends.FriendNew
import io.leonhardt.coffee.latte.groups.GroupCreateService
import io.leonhardt.coffee.latte.groups.GroupNew
import io.leonhardt.coffee.latte.support.closeToNow
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
class CoffeeCreateServiceTest {

    @Autowired
    lateinit var friendCreateService: FriendCreateService

    @Autowired
    lateinit var groupCreateService: GroupCreateService

    val subject: CoffeeCreateService = CoffeeCreateService()

    @Test
    fun `returns the persisted Coffee`() {
        val groupId = GroupNew(name = "Group 1").let { groupCreateService.create(it) }.succeeds().id
        val friend = friendCreateService.create(FriendNew(name = "test-friend", groupId = groupId)).succeeds()
        val coffeeNew = CoffeeNew(friendId = friend.id)

        subject.create(coffeeNew = coffeeNew) succeedsAnd { coffee ->
            assertThat(coffee.friendId, equalTo(friend.id))
            assertThat(coffee.dateTime, closeToNow())
        }
    }
}
