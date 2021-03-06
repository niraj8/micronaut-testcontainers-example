package dev.medly.books.api

import com.amazonaws.serverless.proxy.internal.testutils.AwsProxyRequestBuilder
import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext
import com.amazonaws.services.lambda.runtime.Context
import com.fasterxml.jackson.databind.ObjectMapper
import dev.medly.books.core.models.Book
import dev.medly.books.core.models.BookSaved
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.StringSpec
import io.micronaut.http.HttpHeaders
import io.micronaut.http.HttpMethod
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.function.aws.proxy.MicronautLambdaHandler

class BookRequestHandlerTest : StringSpec({

    "test book controller" {
        val handler = MicronautLambdaHandler()
        val book = Book()
        book.name = "Building Microservices"
        val objectMapper = handler.applicationContext.getBean(ObjectMapper::class.java)
        val json = objectMapper.writeValueAsString(book)
        val request = AwsProxyRequestBuilder("/", HttpMethod.POST.toString())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .body(json)
                .build()
        val lambdaContext: Context = MockLambdaContext()
        val response = handler.handleRequest(request, lambdaContext)
        response.statusCode.shouldBe(HttpStatus.OK.code)
        val bookSaved: BookSaved = objectMapper.readValue(response.body, BookSaved::class.java)
        bookSaved.shouldNotBeNull()
        bookSaved.name.shouldBe(book.name)
        bookSaved.isbn.shouldNotBeNull()
        handler.applicationContext.close()
    }
})
