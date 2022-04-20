package dev.medly.books.repository

import books.*
import dev.medly.books.core.repository.BookRepository
import dev.medly.books.core.models.Book
import dev.medly.books.core.models.BookSaved
import jakarta.inject.Inject
import jakarta.inject.Singleton
import norm.query
import java.util.*
import javax.sql.DataSource

@Singleton
class BookRepositoryImpl(@Inject val datasource: DataSource) : BookRepository {
    override fun getBooks() =
        datasource.connection.use { conn ->
            GetBooksQuery().query(conn, GetBooksParams())
        }.map { it.toBookSaved() }

    override fun addBook(book: Book) =
        datasource.connection.use { conn ->
            AddBookQuery().query(conn, AddBookParams(book.name, UUID.randomUUID().toString()))
        }.map { it.toBookSaved() }.first()
}

private fun GetBooksResult.toBookSaved(): BookSaved = BookSaved(name, isbn)

private fun AddBookResult.toBookSaved(): BookSaved = BookSaved(name, isbn)

//interface BookSavedMapper<R> {
//    fun map(book: R): BookSaved
//}