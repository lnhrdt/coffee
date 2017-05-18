package io.leonhardt.coffee.latte

import org.springframework.http.ResponseEntity

sealed class Result<successType, failureType> {
    data class Success<successType, failureType>(val data: successType) : Result<successType, failureType>()
    data class Failure<successType, failureType>(val errors: failureType) : Result<successType, failureType>()
}

typealias Errors = Map<String, String>
typealias APIResult<type> = ResponseEntity<Result<type, Errors>>
