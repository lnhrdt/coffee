package io.leonhardt.latte.friend

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup
import java.util.*

@RunWith(SpringJUnit4ClassRunner::class)
class FriendGetAllControllerTest {

    private val friendGetAllService: FriendGetAllService = mock(FriendGetAllService::class.java)
    private val subject = FriendGetAllController(friendGetAllService)
    private val mockMvc: MockMvc = standaloneSetup(subject).build()

    @Test
    fun getAll() {
        val friends = listOf(
                Friend(UUID.randomUUID(), "Thomas Shouler"),
                Friend(UUID.randomUUID(), "Alex Thornburg"),
                Friend(UUID.randomUUID(), "Rodolfo Sanchez")
        )

        `when`(friendGetAllService.getAll()).thenReturn(friends)

        mockMvc.perform(get("/friend"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.length()").value(3))
    }
}
