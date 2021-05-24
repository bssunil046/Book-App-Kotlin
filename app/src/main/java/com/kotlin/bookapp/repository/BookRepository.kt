package com.kotlin.bookapp.repository

import androidx.lifecycle.MutableLiveData
import com.kotlin.bookapp.api.BookService
import com.kotlin.bookapp.model.BookResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BookRepository {
    private val base_url: String = "https://www.googleapis.com/"
    private val bookService: BookService
    val bookResponseLiveData : MutableLiveData<BookResponse> by lazy {
        MutableLiveData<BookResponse>()
    }

    init {
        bookService = Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(BookService::class.java)
    }

    fun searchBooks(keyword:String, author:String) {
        val call = bookService.getBooks(query = keyword, author = author)
        call.enqueue(object : Callback<BookResponse> {
            override fun onFailure(call: Call<BookResponse>?, t: Throwable?) {
                print(t?.localizedMessage)
                bookResponseLiveData.postValue(null)
            }

            override fun onResponse(call: Call<BookResponse>?, response: Response<BookResponse>?) {
                if(response?.code() == 200) {
                    print(response.body())
                    bookResponseLiveData.postValue(response.body())
                }
            }
        })
    }
}