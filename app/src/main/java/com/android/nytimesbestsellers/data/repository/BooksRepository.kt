package com.android.nytimesbestsellers.data.repository

import com.android.nytimesbestsellers.data.BooksResult

interface BooksRepository {

    fun getBooks(booksResultCallback: (result: BooksResult) -> Unit)

}