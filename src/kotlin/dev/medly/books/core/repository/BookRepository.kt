package dev.medly.books.core.repository

import dev.medly.books.core.models.Book
import dev.medly.books.core.models.BookSaved

interface BookRepository {
    fun getBooks(): List<BookSaved>
    fun addBook(book: Book): BookSaved
}