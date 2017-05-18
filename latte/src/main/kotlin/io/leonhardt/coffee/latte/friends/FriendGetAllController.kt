package io.leonhardt.coffee.latte.friends

import io.leonhardt.coffee.latte.APIResult
import io.leonhardt.coffee.latte.Result
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class FriendGetAllController(val friendGetAllService: FriendGetAllService) {
    @GetMapping("/api/friends")
    fun getAll(): APIResult<List<Friend>> = ResponseEntity.ok().body(Result.Success(friendGetAllService.getAll()))
}
