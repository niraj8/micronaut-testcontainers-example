package dev.medly.books.core.models
import io.micronaut.core.annotation.Introspected

@Introspected
data class BookSaved (
    var name: String? = null,
    var isbn: String? = null
)