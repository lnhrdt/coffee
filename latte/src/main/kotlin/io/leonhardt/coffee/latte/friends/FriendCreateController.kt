package io.leonhardt.coffee.latte.friends

import io.leonhardt.coffee.latte.APIResult
import io.leonhardt.coffee.latte.Result
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class FriendCreateController(val friendCreateService: FriendCreateService) {
    @PostMapping("/api/friends")
    fun create(@RequestBody request: FriendCreateService.Request): APIResult<Friend> {
        val createResult = friendCreateService.create(request)
        return when (createResult) {
            is Result.Success -> ResponseEntity.ok().body(createResult)
            is Result.Failure -> ResponseEntity.badRequest().body(createResult)
        }
    }
}
