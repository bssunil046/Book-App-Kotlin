package com.kotlin.bookapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kotlin.bookapp.api.BookService
import com.kotlin.bookapp.model.BookResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        val service = retrofit.create(BookService::class.java)
        val call = service.getBooks(query = "harry", author = "");
        call.enqueue(object : Callback<BookResponse> {
            override fun onFailure(call: Call<BookResponse>?, t: Throwable?) {
                print(t?.localizedMessage)
            }

            override fun onResponse(call: Call<BookResponse>?, response: Response<BookResponse>?) {
                if(response?.code() == 200) {
                    print(response.body())
                }
            }
        })
    }
}