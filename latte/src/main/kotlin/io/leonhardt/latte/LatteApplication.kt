package io.leonhardt.latte

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class LatteApplication

fun main(args: Array<String>) {
    SpringApplication.run(LatteApplication::class.java, *args)
}
