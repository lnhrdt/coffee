package io.leonhardt.coffee.latte

interface Translator<in inType, out outType> {
    fun translate(input: inType): outType
}
