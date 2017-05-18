package io.leonhardt.coffee.latte.coffee

import io.leonhardt.coffee.latte.APIResult
import io.leonhardt.coffee.latte.Result
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CoffeeCreateController(val coffeeService: CoffeeService) {
    @PostMapping("/api/coffees")
    fun create(@RequestBody createRequest: CoffeeService.CreateRequest): APIResult<Unit> {
        return when (coffeeService.create(createRequest)) {
            is Result.Success -> ResponseEntity.ok(Result.Success(Unit))
            is Result.Failure -> TODO()
        }
    }
}
