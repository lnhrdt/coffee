package io.leonhardt.coffee.latte.friends

import io.leonhardt.coffee.latte.APIResponse
import io.leonhardt.coffee.latte.toResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class FriendFindAllByGroupController(val friendFindAllByGroupService: FriendFindAllByGroupService) {
    @GetMapping("/api/groups/{groupId}/friends")
    fun getAll(@PathVariable groupId: UUID): APIResponse<List<Friend>> =
        friendFindAllByGroupService.findAllByGroup(groupId = groupId).toResponseEntity()
}
