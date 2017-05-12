package io.leonhardt.latte.friends

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class FriendGetAllController(val friendGetAllService: FriendGetAllService) {
    @RequestMapping(value = "/friends", method = arrayOf(RequestMethod.GET))
    fun getAll(): Iterable<Friend> = friendGetAllService.getAll()
}
