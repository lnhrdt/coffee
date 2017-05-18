package io.leonhardt.coffee.latte.coffee

import com.nhaarman.mockito_kotlin.argForWhich
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.leonhardt.coffee.latte.Result
import io.leonhardt.coffee.latte.support.jsonBody
import org.hamcrest.Matchers.hasKey
import org.hamcrest.Matchers.not
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

        whenever(coffeeService.create(createRequest)).thenReturn(Result.Success(Unit))

        mockMvc.perform(post("/api/coffees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody(createRequest)))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.data.length()").value(0))
                .andExpect(jsonPath("$", not(hasKey("errors"))))

        verify(coffeeService).create(argForWhich { friendId == givenFriendId })
    }
}
