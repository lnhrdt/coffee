package io.leonhardt.coffee.latte.friends

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

@RunWith(SpringRunner::class)
class FriendGetAllControllerTest {

    private val friendGetAllService: FriendGetAllService = mock()
    private val subject = FriendGetAllController(friendGetAllService)
    private val mockMvc: MockMvc = standaloneSetup(subject).build()

    @Test
    fun getAll() {
        val friends = listOf(
                Friend(name = "Thomas Shouler", coffees = emptyList()),
                Friend(name = "Alex Thornburg", coffees = emptyList()),
                Friend(name = "Rodolfo Sanchez", coffees = emptyList())
        )

        whenever(friendGetAllService.getAll()).thenReturn(friends)

        mockMvc.perform(get("/api/friends"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.data.length()").value(3))
    }
}
