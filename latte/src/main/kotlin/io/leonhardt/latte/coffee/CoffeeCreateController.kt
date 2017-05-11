package io.leonhardt.latte.coffee

import org.springframework.http.HttpEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class CoffeeCreateController {
    @RequestMapping(value = "/coffee", method = arrayOf(RequestMethod.POST))
    fun create(): HttpEntity<*> = ResponseEntity.EMPTY
}
