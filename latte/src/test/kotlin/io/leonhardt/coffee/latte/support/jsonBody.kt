package io.leonhardt.coffee.latte.support

import com.fasterxml.jackson.databind.ObjectMapper

fun jsonBody(body: Any): String = ObjectMapper().writer().writeValueAsString(body)
