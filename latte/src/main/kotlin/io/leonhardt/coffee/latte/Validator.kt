package io.leonhardt.coffee.latte

import io.github.codebandits.results.Result

interface Validator<DataType> {
    fun validate(data: DataType): Result<Errors, DataType>
}
