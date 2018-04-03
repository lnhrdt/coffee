package io.leonhardt.coffee.latte

import arrow.core.Either
import org.springframework.http.ResponseEntity

typealias Errors = Map<String, String>

sealed class APIResult<DataType> {
    data class Success<DataType>(val data: DataType) : APIResult<DataType>()
    data class Failure<DataType>(val errors: Errors) : APIResult<DataType>()
}

typealias APIResponse<DataType> = ResponseEntity<APIResult<DataType>>

fun <T> Either<Errors, T>.toResponseEntity(): APIResponse<T> = when (this) {
    is Either.Right -> ResponseEntity.ok().body(APIResult.Success(b))
    is Either.Left  -> ResponseEntity.badRequest().body(APIResult.Failure(a))
}
