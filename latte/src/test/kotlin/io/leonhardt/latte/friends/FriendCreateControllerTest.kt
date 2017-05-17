package io.leonhardt.latte.friends

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.leonhardt.latte.support.jsonBody
import org.hamcrest.Matchers.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup
import java.util.*

@RunWith(SpringRunner::class)
class FriendCreateControllerTest {

    private val friendCreateService: FriendCreateService = mock()
    private val subject = FriendCreateController(friendCreateService)
    private val mockMvc: MockMvc = standaloneSetup(subject).build()

    @Test
    fun create_whenNoErrors_shouldReturnFriend() {
        val friend = Friend(id = UUID.randomUUID(), name = "William Ramsey", coffees = emptyList())
        val request = FriendCreateService.Request(name = "William Ramsey")
        whenever(friendCreateService.create(request)).thenReturn(FriendCreateService.Response(friend = friend))

        mockMvc.perform(post("/api/friends")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody(request)))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.data.id", equalTo(friend.id.toString())))
                .andExpect(jsonPath("$", not(hasKey("errors"))))
    }

    @Test
    fun create_whenErrors_shouldReturnErrors() {
        val request = FriendCreateService.Request(name = "William Ramsey")
        val errors = mapOf("mistake" to "you did it wrong")
        whenever(friendCreateService.create(request)).thenReturn(FriendCreateService.Response(errors = errors))

        mockMvc.perform(post("/api/friends")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody(request)))
                .andExpect(status().isBadRequest)
                .andExpect(jsonPath("$", not(hasKey("data"))))
                .andExpect(jsonPath("$.errors.length()", equalTo(1)))
                .andExpect(jsonPath("$.errors.mistake", equalTo("you did it wrong")))
    }
}
