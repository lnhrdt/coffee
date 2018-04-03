package io.leonhardt.coffee.latte.coffee

import io.leonhardt.coffee.latte.APIResponse
import io.leonhardt.coffee.latte.toResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CoffeeCreateController(val coffeeCreateService: CoffeeCreateService) {
    @PostMapping("/api/coffees")
    fun create(@RequestBody coffeeNew: CoffeeNew): APIResponse<Coffee> =
        coffeeCreateService.create(coffeeNew).toResponseEntity()
}
