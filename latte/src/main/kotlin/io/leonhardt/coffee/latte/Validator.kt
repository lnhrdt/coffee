package io.leonhardt.coffee.latte

import arrow.core.Either

interface Validator<DataType> {
    fun validate(data: DataType): Either<Errors, DataType>
}
