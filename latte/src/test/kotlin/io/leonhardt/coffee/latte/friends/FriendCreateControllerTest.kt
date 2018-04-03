package io.leonhardt.coffee.latte.friends

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.github.codebandits.results.Failure
import io.github.codebandits.results.Success
import io.leonhardt.coffee.latte.support.asJson
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup
import java.util.*

class FriendCreateControllerTest {

    private val friendCreateService: FriendCreateService = mock()
    private val subject = FriendCreateController(friendCreateService)
    private val mockMvc: MockMvc = standaloneSetup(subject).build()

    @Test
    fun `when service succeeds, returns the Friend`() {
        val groupId = UUID.randomUUID()
        val request = FriendNew(name = "William Ramsey", groupId = groupId)
        val friend = Friend(id = UUID.randomUUID(), name = "William Ramsey", coffees = emptyList(), groupId = groupId)
        whenever(friendCreateService.create(request)).thenReturn(Success(friend))

        mockMvc.perform(post("/api/friends")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request.asJson))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.data.id", equalTo(friend.id.toString())))
                .andExpect(jsonPath("$", not(hasKey("errors"))))
    }

    @Test
    fun `when service fails, returns the errors`() {
        val groupId = UUID.randomUUID()
        val request = FriendNew(name = "William Ramsey", groupId = groupId)
        val errors = mapOf("mistake" to "you did it wrong")
        whenever(friendCreateService.create(request)).thenReturn(Failure(errors))

        mockMvc.perform(post("/api/friends")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request.asJson))
                .andExpect(status().isBadRequest)
                .andExpect(jsonPath("$", not(hasKey("data"))))
                .andExpect(jsonPath("$.errors.length()", equalTo(1)))
                .andExpect(jsonPath("$.errors.mistake", equalTo("you did it wrong")))
    }
}
