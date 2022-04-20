package dev.medly.books

import io.micronaut.runtime.Micronaut.*
fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("dev.medly.books")
		.start()
}

