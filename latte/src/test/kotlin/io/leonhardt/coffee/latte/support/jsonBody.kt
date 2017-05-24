package io.leonhardt.coffee.latte.support

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

private val internalObjectMapper = ObjectMapper().registerKotlinModule()
val Any.asJson: String get() = internalObjectMapper.writeValueAsString(this)
