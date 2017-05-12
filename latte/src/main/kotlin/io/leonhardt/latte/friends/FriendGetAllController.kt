package io.leonhardt.latte.friends

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class FriendGetAllController(val friendGetAllService: FriendGetAllService) {
    @GetMapping("/friends")
    fun getAll(): Iterable<Friend> = friendGetAllService.getAll()
}
