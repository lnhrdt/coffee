package io.leonhardt.coffee.latte.coffee

import io.github.codebandits.results.Failure
import io.github.codebandits.results.Success
import io.leonhardt.coffee.latte.APIResponse
import io.leonhardt.coffee.latte.APIResult
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CoffeeCreateController(val coffeeCreateService: CoffeeCreateService) {
    @PostMapping("/api/coffees")
    fun create(@RequestBody coffeeNew: CoffeeNew): APIResponse<Coffee> {
        val result = coffeeCreateService.create(coffeeNew)
        return when (result) {
            is Success -> ResponseEntity.ok().body(APIResult.Success(result.content))
            is Failure -> ResponseEntity.badRequest().body(APIResult.Failure(result.content))
        }
    }
}
