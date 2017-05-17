package io.leonhardt.latte.friends

import io.leonhardt.latte.APIResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class FriendCreateController(val friendCreateService: FriendCreateService) {
    @PostMapping("/api/friends")
    fun create(@RequestBody request: FriendCreateService.Request): ResponseEntity<APIResponse> {
        val response = friendCreateService.create(request)
        if (response.errors.isNotEmpty()) {
            return ResponseEntity.badRequest().body(APIResponse(errors = response.errors))
        }
        return ResponseEntity.ok().body(APIResponse(data = response.friend))
    }
}
