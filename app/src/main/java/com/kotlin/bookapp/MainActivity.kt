package com.kotlin.bookapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.kotlin.bookapp.model.BookResponse
import com.kotlin.bookapp.viewmodel.BookViewModel

class MainActivity : AppCompatActivity() {
    private val bookViewModel: BookViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bookViewModel.booksResponseLiveData.observe(this, Observer<BookResponse> { bookResponse ->
            Log.i("MainActivity", bookResponse.items.first().volumeInfo.title)
        })
        bookViewModel.getBooks("harry potter", "")
    }
}