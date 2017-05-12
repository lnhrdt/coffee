package io.leonhardt.latte.friends

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

@RunWith(SpringJUnit4ClassRunner::class)
class FriendGetAllControllerTest {

    private val friendGetAllService: FriendGetAllService = mock(FriendGetAllService::class.java)
    private val subject = FriendGetAllController(friendGetAllService)
    private val mockMvc: MockMvc = standaloneSetup(subject).build()

    @Test
    fun getAll() {
        val friends = listOf(
                Friend(name = "Thomas Shouler"),
                Friend(name = "Alex Thornburg"),
                Friend(name = "Rodolfo Sanchez")
        )

        `when`(friendGetAllService.getAll()).thenReturn(friends)

        mockMvc.perform(get("/friends"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.length()").value(3))
    }
}
