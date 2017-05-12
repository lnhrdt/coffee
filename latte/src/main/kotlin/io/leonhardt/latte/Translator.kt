package io.leonhardt.latte

interface Translator<in inType, out outType> {
    fun translate(input: inType): outType
}
