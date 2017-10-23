package io.leonhardt.coffee.latte.friends

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.github.codebandits.results.Failure
import io.github.codebandits.results.Success
import org.hamcrest.Matchers
import org.junit.Test
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup
import java.util.*

class FriendGetAllControllerTest {

    private val friendGetAllService: FriendGetAllService = mock()
    private val subject = FriendGetAllController(friendGetAllService)
    private val mockMvc: MockMvc = standaloneSetup(subject).build()

    @Test
    fun `when service succeeds, returns the Friends`() {
        val friends = listOf(
                Friend(id = UUID.randomUUID(), name = "Thomas Shouler", coffees = emptyList()),
                Friend(id = UUID.randomUUID(), name = "Alex Thornburg", coffees = emptyList()),
                Friend(id = UUID.randomUUID(), name = "Rodolfo Sanchez", coffees = emptyList())
        )

        whenever(friendGetAllService.getAll()).thenReturn(Success(friends))

        mockMvc.perform(get("/api/friends"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.data.length()").value(3))
    }

    @Test
    fun `when service fails, returns the errors`() {
        val errors = mapOf("mistake" to "you did it wrong")
        whenever(friendGetAllService.getAll()).thenReturn(Failure(errors))

        mockMvc.perform(get("/api/friends"))
                .andExpect(status().isBadRequest)
                .andExpect(jsonPath("$", Matchers.not(Matchers.hasKey("data"))))
                .andExpect(jsonPath("$.errors.length()", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.errors.mistake", Matchers.equalTo("you did it wrong")))
    }
}
