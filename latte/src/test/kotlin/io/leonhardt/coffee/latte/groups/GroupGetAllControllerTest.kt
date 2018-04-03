package io.leonhardt.coffee.latte.groups

import arrow.core.Either
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.util.*

class GroupGetAllControllerTest {

    private val groupGetAllService: GroupGetAllService = mock()
    private val subject = GroupGetAllController(groupGetAllService)
    private val mockMvc: MockMvc = MockMvcBuilders.standaloneSetup(subject).build()

    @Test
    fun `when service succeeds, returns the Groups`() {
        val groups = listOf(
                Group(id = UUID.randomUUID(), name = "Group 1", friends = emptyList()),
                Group(id = UUID.randomUUID(), name = "Group 2", friends = emptyList()),
                Group(id = UUID.randomUUID(), name = "Group 3", friends = emptyList())
        )

        whenever(groupGetAllService.getAll()).thenReturn(Either.right(groups))

        mockMvc.perform(MockMvcRequestBuilders.get("/api/groups"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.length()").value(3))
    }

    @Test
    fun `when service fails, returns the errors`() {
        val errors = mapOf("mistake" to "you did it wrong")
        whenever(groupGetAllService.getAll()).thenReturn(Either.left(errors))

        mockMvc.perform(MockMvcRequestBuilders.get("/api/groups"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest)
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.not(Matchers.hasKey("data"))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors.length()", Matchers.equalTo(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors.mistake", Matchers.equalTo("you did it wrong")))
    }
}
