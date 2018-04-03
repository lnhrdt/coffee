package io.leonhardt.coffee.latte.friends

import io.leonhardt.coffee.latte.APIResponse
import io.leonhardt.coffee.latte.toResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class FriendCreateController(val friendCreateService: FriendCreateService) {
    @PostMapping("/api/friends")
    fun create(@RequestBody friendNew: FriendNew): APIResponse<Friend> =
        friendCreateService.create(friendNew).toResponseEntity()
}

