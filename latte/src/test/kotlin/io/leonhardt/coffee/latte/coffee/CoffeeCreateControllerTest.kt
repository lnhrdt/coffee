package io.leonhardt.coffee.latte.coffee

import com.nhaarman.mockito_kotlin.argForWhich
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.leonhardt.coffee.latte.support.jsonBody
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.util.*

@RunWith(SpringRunner::class)
class CoffeeCreateControllerTest {

    private val coffeeService: CoffeeService = mock()
    private val subject = CoffeeCreateController(coffeeService)
    private val mockMvc: MockMvc = MockMvcBuilders.standaloneSetup(subject).build()

    @Test
    fun create() {

        val givenFriendId = UUID.randomUUID()
        val createRequest = CoffeeService.CreateRequest(friendId = givenFriendId)

        mockMvc.perform(post("/api/coffees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody(createRequest)))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.length()").value(0))

        verify(coffeeService).create(argForWhich { friendId == givenFriendId })
    }
}
