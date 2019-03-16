package io.leonhardt.coffee.latte

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class LatteApplication

fun main(args: Array<String>) {
    SpringApplication.run(LatteApplication::class.java, *args)
}
