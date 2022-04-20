package dev.medly.books.api

import dev.medly.books.core.models.Book
import dev.medly.books.core.repository.BookRepository
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import jakarta.inject.Inject
import javax.validation.Valid

@Controller
open class BookController(
    @Inject val bookRepository: BookRepository  // this should be a service instead, for this example the repository is fine
): BookApi {
    @Post
    override fun save(@Valid @Body book: Book) = bookRepository.addBook(book)
    override fun getBooks() = bookRepository.getBooks()
}