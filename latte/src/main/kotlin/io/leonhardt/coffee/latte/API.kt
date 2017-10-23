package io.leonhardt.coffee.latte

import org.springframework.http.ResponseEntity

typealias Errors = Map<String, String>

sealed class APIResult<DataType> {
    data class Success<DataType>(val data: DataType) : APIResult<DataType>()
    data class Failure<DataType>(val errors: Errors) : APIResult<DataType>()
}

typealias APIResponse<DataType> = ResponseEntity<APIResult<DataType>>
