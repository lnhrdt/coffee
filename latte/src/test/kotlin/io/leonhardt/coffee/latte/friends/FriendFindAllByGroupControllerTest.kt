package io.leonhardt.coffee.latte.friends

import arrow.core.Either
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup
import java.util.*

class FriendFindAllByGroupControllerTest {

    private val friendFindAllByGroupService: FriendFindAllByGroupService = mock()
    private val subject = FriendFindAllByGroupController(friendFindAllByGroupService)
    private val mockMvc: MockMvc = standaloneSetup(subject).build()

    @Test
    fun `when service succeeds, returns the Friends`() {
        val groupId = UUID.randomUUID()
        val friends = listOf(
                Friend(id = UUID.randomUUID(), name = "Thomas Shouler", coffees = emptyList(), groupId = groupId),
                Friend(id = UUID.randomUUID(), name = "Alex Thornburg", coffees = emptyList(), groupId = groupId),
                Friend(id = UUID.randomUUID(), name = "Rodolfo Sanchez", coffees = emptyList(), groupId = groupId)
        )

        whenever(friendFindAllByGroupService.findAllByGroup(groupId)).thenReturn(Either.right(friends))

        mockMvc.perform(get("/api/groups/$groupId/friends"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.data.length()").value(3))
    }

    @Test
    fun `when service fails, returns the errors`() {
        val groupId = UUID.randomUUID()
        val errors = mapOf("mistake" to "you did it wrong")
        whenever(friendFindAllByGroupService.findAllByGroup(groupId)).thenReturn(Either.left(errors))

        mockMvc.perform(get("/api/groups/$groupId/friends"))
                .andExpect(status().isBadRequest)
                .andExpect(jsonPath("$", Matchers.not(Matchers.hasKey("data"))))
                .andExpect(jsonPath("$.errors.length()", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.errors.mistake", Matchers.equalTo("you did it wrong")))
    }
}
