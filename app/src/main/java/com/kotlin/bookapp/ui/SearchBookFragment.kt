package com.kotlin.bookapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.kotlin.bookapp.R
import com.kotlin.bookapp.model.BookResponse
import com.kotlin.bookapp.viewmodel.BookViewModel

class SearchBookFragment : Fragment(R.layout.fragment_search_book) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val edtQuery = view.findViewById<EditText>(R.id.edit_query)
        val edtAuthor = view.findViewById<EditText>(R.id.edit_author)
        val btnSubmit = view.findViewById<Button>(R.id.btn_submit)

        btnSubmit.setOnClickListener(View.OnClickListener {
            submitClicked(view, edtQuery.text.toString(), edtAuthor.text.toString())
        })
    }

    private fun submitClicked(view: View, query: String, author: String ) {
        val bookViewModel: BookViewModel by viewModels()
        bookViewModel.booksResponseLiveData.observe(requireActivity(), Observer<BookResponse> { bookResponse ->
            if(bookResponse != null && bookResponse.items.isNotEmpty()) {
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.add(R.id.fragment_container_view, SearchResultFragment())
                transaction.commit()
            } else {
                Snackbar.make(requireActivity(), view, "Unable to fetch data", Snackbar.LENGTH_LONG).show()
            }
        })
        bookViewModel.getBooks(query, author)
    }
}