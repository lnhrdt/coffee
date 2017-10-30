package io.leonhardt.coffee.latte.groups

import io.github.codebandits.results.Failure
import io.github.codebandits.results.Success
import io.leonhardt.coffee.latte.APIResponse
import io.leonhardt.coffee.latte.APIResult
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GroupGetAllController(val groupGetAllService: GroupGetAllService) {
    @GetMapping("/api/groups")
    fun getAll(): APIResponse<List<Group>> {
        val result = groupGetAllService.getAll()

        return when (result) {
            is Success -> ResponseEntity.ok(APIResult.Success(result.content))
            is Failure -> ResponseEntity.badRequest().body(APIResult.Failure(result.content))
        }
    }
}
