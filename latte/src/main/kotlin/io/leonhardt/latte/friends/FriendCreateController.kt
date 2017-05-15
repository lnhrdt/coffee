package io.leonhardt.latte.friends

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class FriendCreateController(val friendCreateService: FriendCreateService) {
    @PostMapping("/api/friends")
    fun create(@RequestBody request: FriendCreateService.Request): Friend = friendCreateService.create(request)
}
