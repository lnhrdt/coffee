package io.leonhardt.coffee.latte.groups

import io.leonhardt.coffee.latte.APIResponse
import io.leonhardt.coffee.latte.toResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GroupGetAllController(val groupGetAllService: GroupGetAllService) {
    @GetMapping("/api/groups")
    fun getAll(): APIResponse<List<Group>> = groupGetAllService.getAll().toResponseEntity()
}
