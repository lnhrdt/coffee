package io.leonhardt.coffee.latte

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class APIResponse(
        val data: Any? = null,
        val errors: Map<String, String> = emptyMap()
)
