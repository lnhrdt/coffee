package io.leonhardt.latte

interface Validator<in inType> {
    fun validate(input: inType): Map<String, String>
}
