package io.leonhardt.coffee.latte.friends

import io.github.codebandits.results.Failure
import io.github.codebandits.results.Success
import io.leonhardt.coffee.latte.APIResponse
import io.leonhardt.coffee.latte.APIResult
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class FriendFindAllByGroupController(val friendFindAllByGroupService: FriendFindAllByGroupService) {
    @GetMapping("/api/groups/{groupId}/friends")
    fun getAll(@PathVariable groupId: UUID): APIResponse<List<Friend>> {
        val result = friendFindAllByGroupService.findAllByGroup(groupId = groupId)

        return when (result) {
            is Success -> ResponseEntity.ok(APIResult.Success(result.content))
            is Failure -> ResponseEntity.badRequest().body(APIResult.Failure(result.content))
        }
    }
}
