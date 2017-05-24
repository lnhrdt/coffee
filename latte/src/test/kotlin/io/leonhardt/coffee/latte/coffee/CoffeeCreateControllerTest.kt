package io.leonhardt.coffee.latte.coffee

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.leonhardt.coffee.latte.Result
import io.leonhardt.coffee.latte.support.asJson
import org.hamcrest.Matchers.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.time.Instant
import java.util.*

@RunWith(SpringRunner::class)
class CoffeeCreateControllerTest {

    private val coffeeCreateService: CoffeeCreateService = mock()
    private val subject = CoffeeCreateController(coffeeCreateService)
    private val mockMvc: MockMvc = MockMvcBuilders.standaloneSetup(subject).build()

    @Test
    fun create_whenResultIsSuccess_shouldReturnCoffee() {
        val request = CoffeeCreateService.Request(friendId = UUID.randomUUID())
        val coffee = Coffee(id = UUID.randomUUID(), friendId = request.friendId, dateTime = Instant.now())
        whenever(coffeeCreateService.create(request)).thenReturn(Result.Success(coffee))

        mockMvc.perform(post("/api/coffees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request.asJson))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.data.id", equalTo(coffee.id.toString())))
                .andExpect(jsonPath("$", not(hasKey("errors"))))
    }

    @Test
    fun create_whenResultIsFailure_shouldReturnErrors() {
        val request = CoffeeCreateService.Request(friendId = UUID.randomUUID())
        val errors = mapOf("mistake" to "you did it wrong")
        whenever(coffeeCreateService.create(request)).thenReturn(Result.Failure(errors))

        mockMvc.perform(post("/api/coffees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request.asJson))
                .andExpect(status().isBadRequest)
                .andExpect(jsonPath("$", not(hasKey("data"))))
                .andExpect(jsonPath("$.errors.length()", equalTo(1)))
                .andExpect(jsonPath("$.errors.mistake", equalTo("you did it wrong")))
    }
}
