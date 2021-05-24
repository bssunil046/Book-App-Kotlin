package com.kotlin.bookapp.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotlin.bookapp.model.BookResponse
import com.kotlin.bookapp.repository.BookRepository

class BookViewModel : ViewModel() {
    private val bookRepository: BookRepository by lazy {
        BookRepository()
    }
    val booksResponseLiveData:MutableLiveData<BookResponse> by lazy {
        bookRepository.bookResponseLiveData
    }

    fun getBooks(keyword: String, author: String) {
        bookRepository.searchBooks(keyword, author)
    }
}