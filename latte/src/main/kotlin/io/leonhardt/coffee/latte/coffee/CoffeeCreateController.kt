package io.leonhardt.coffee.latte.coffee

import io.leonhardt.coffee.latte.APIResult
import io.leonhardt.coffee.latte.Result
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CoffeeCreateController(val coffeeCreateService: CoffeeCreateService) {
    @PostMapping("/api/coffees")
    fun create(@RequestBody request: CoffeeCreateService.Request): APIResult<Coffee> {
        val createResult = coffeeCreateService.create(request)
        return when (createResult) {
            is Result.Success -> ResponseEntity.ok().body(createResult)
            is Result.Failure -> ResponseEntity.badRequest().body(createResult)
        }
    }
}
