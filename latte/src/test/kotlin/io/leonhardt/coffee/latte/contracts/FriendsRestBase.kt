package io.leonhardt.coffee.latte.contracts

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.leonhardt.coffee.latte.friends.Friend
import io.leonhardt.coffee.latte.friends.FriendGetAllController
import io.leonhardt.coffee.latte.friends.FriendGetAllService
import org.junit.Before

abstract class FriendsRestBase {
    @Before
    fun setup() {
        val friendGetAllService: FriendGetAllService = mock()
        RestAssuredMockMvc.standaloneSetup(FriendGetAllController(friendGetAllService))

        val friends = listOf(Friend(name = "bob", coffees = emptyList()))
        whenever(friendGetAllService.getAll()).thenReturn(friends)
    }
}
