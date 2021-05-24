package com.kotlin.bookapp.api

import com.kotlin.bookapp.model.BookResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {
    @GET("/books/v1/volumes")
    fun getBooks(
        @Query("q") query: String,
        @Query("inauthor") author: String
    ): Call<BookResponse>
}