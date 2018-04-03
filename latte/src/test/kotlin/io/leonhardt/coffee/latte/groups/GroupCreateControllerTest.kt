package io.leonhardt.coffee.latte.groups

import arrow.core.Either
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.leonhardt.coffee.latte.support.asJson
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.util.*

class GroupCreateControllerTest {

    private val groupCreateService: GroupCreateService = mock()
    private val subject = GroupCreateController(groupCreateService)
    private val mockMvc: MockMvc = MockMvcBuilders.standaloneSetup(subject).build()

    @Test
    fun `when service succeeds, returns the Group`() {
        val request = GroupNew(name = "Singapore")
        val group = Group(id = UUID.randomUUID(), name = "Singapore", friends = emptyList())
        whenever(groupCreateService.create(request)).thenReturn(Either.right(group))

        mockMvc.perform(post("/api/groups").contentType(MediaType.APPLICATION_JSON).content(request.asJson))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.data.id", Matchers.equalTo(group.id.toString())))
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.not(Matchers.hasKey("errors"))))
    }

    @Test
    fun `when service fails, returns the errors`() {
        val request = GroupNew(name = "Singapore")
        val errors = mapOf("mistake" to "you did it wrong")
        whenever(groupCreateService.create(request)).thenReturn(Either.left(errors))

        mockMvc.perform(post("/api/groups").contentType(MediaType.APPLICATION_JSON).content(request.asJson))
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.not(Matchers.hasKey("data"))))
            .andExpect(MockMvcResultMatchers.jsonPath("$.errors.length()", Matchers.equalTo(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.errors.mistake", Matchers.equalTo("you did it wrong")))
    }
}
