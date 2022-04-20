package dev.medly.books.core.models
import io.micronaut.core.annotation.Introspected

@Introspected
data class Book (
    var name: String? = null
)