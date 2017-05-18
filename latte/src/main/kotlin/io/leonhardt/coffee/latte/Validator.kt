package io.leonhardt.coffee.latte

interface Validator<in inType> {
    fun validate(input: inType): Result<Unit, Errors>
}
