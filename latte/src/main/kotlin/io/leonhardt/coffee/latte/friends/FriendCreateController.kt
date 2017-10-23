package io.leonhardt.coffee.latte.friends

import io.github.codebandits.results.Failure
import io.github.codebandits.results.Success
import io.leonhardt.coffee.latte.APIResponse
import io.leonhardt.coffee.latte.APIResult
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class FriendCreateController(val friendCreateService: FriendCreateService) {
    @PostMapping("/api/friends")
    fun create(@RequestBody friendNew: FriendNew): APIResponse<Friend> {
        val result = friendCreateService.create(friendNew)

        return when (result) {
            is Success -> ResponseEntity.ok().body(APIResult.Success(result.content))
            is Failure -> ResponseEntity.badRequest().body(APIResult.Failure(result.content))
        }
    }
}
