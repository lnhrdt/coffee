package io.leonhardt.latte.friends

import io.leonhardt.latte.APIResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class FriendGetAllController(val friendGetAllService: FriendGetAllService) {
    @GetMapping("/api/friends")
    fun getAll(): ResponseEntity<APIResponse> = ResponseEntity.ok().body(APIResponse(data = friendGetAllService.getAll()))
}
