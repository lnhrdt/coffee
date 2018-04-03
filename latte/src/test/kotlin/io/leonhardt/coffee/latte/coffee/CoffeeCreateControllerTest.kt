package io.leonhardt.coffee.latte.coffee

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.time.Instant
import java.util.*

class CoffeeCreateControllerTest {

    private val coffeeCreateService: CoffeeCreateService = mock()
    private val subject = CoffeeCreateController(coffeeCreateService)
    private val mockMvc: MockMvc = MockMvcBuilders.standaloneSetup(subject).build()

    @Test
    fun `when service succeeds, returns the Coffee`() {
        val request = CoffeeNew(friendId = UUID.randomUUID())
        val coffee = Coffee(id = UUID.randomUUID(), friendId = request.friendId, dateTime = Instant.now())
        whenever(coffeeCreateService.create(request)).thenReturn(Success(coffee))

        mockMvc.perform(post("/api/coffees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request.asJson))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.data.id", equalTo(coffee.id.toString())))
                .andExpect(jsonPath("$", not(hasKey("errors"))))
    }

    @Test
    fun `when service fails, returns the errors`() {
        val request = CoffeeNew(friendId = UUID.randomUUID())
        val errors = mapOf("mistake" to "you did it wrong")
        whenever(coffeeCreateService.create(request)).thenReturn(Failure(errors))

        mockMvc.perform(post("/api/coffees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request.asJson))
                .andExpect(status().isBadRequest)
                .andExpect(jsonPath("$", not(hasKey("data"))))
                .andExpect(jsonPath("$.errors.length()", equalTo(1)))
                .andExpect(jsonPath("$.errors.mistake", equalTo("you did it wrong")))
    }
}
