package io.leonhardt.latte.coffee

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CoffeeCreateController(val coffeeService: CoffeeService) {
    @PostMapping("/api/coffees")
    fun create(@RequestBody createRequest: CoffeeService.CreateRequest): ResponseEntity<Unit> {
        coffeeService.create(createRequest)
        return ResponseEntity.ok(Unit)
    }
}
