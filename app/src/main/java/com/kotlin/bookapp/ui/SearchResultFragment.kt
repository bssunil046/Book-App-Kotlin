package com.kotlin.bookapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.bookapp.R
import com.kotlin.bookapp.viewmodel.BookViewModel

class SearchResultFragment : Fragment(R.layout.fragment_search_result) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bookViewModel: BookViewModel by viewModels()
        val recyclerView = view.findViewById<RecyclerView>(R.id.list_books)
    }
}