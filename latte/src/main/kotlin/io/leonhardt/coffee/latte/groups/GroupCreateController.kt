package io.leonhardt.coffee.latte.groups

import io.leonhardt.coffee.latte.APIResponse
import io.leonhardt.coffee.latte.toResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class GroupCreateController(val groupCreateService: GroupCreateService) {
    @PostMapping("/api/groups")
    fun create(@RequestBody groupNew: GroupNew): APIResponse<Group> =
        groupCreateService.create(groupNew).toResponseEntity()
}
