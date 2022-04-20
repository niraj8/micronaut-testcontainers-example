package dev.medly.books.api

import dev.medly.books.core.models.Book
import dev.medly.books.core.models.BookSaved

interface BookApi {
    fun getBooks(): List<BookSaved>
    fun save(book: Book): BookSaved
}