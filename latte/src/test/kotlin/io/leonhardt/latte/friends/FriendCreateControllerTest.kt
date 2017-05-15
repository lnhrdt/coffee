package io.leonhardt.latte.friends

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.leonhardt.latte.support.jsonBody
import org.hamcrest.Matchers.equalTo
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
    fun getAll() {
        val friend = Friend(id = UUID.randomUUID(), name = "William Ramsey", coffees = emptyList())
        val request = FriendCreateService.Request(name = "William Ramsey")
        whenever(friendCreateService.create(request)).thenReturn(friend)

        mockMvc.perform(post("/api/friends")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody(request)))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.id", equalTo(friend.id.toString())))
    }
}
