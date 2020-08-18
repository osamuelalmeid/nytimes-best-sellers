package com.android.nytimesbestsellers.data

import com.android.nytimesbestsellers.data.model.Book

sealed class BooksResult {

    class Sucess(val books: List<Book>) : BooksResult()
    class ApiError(val statusCode: Int) : BooksResult()
    object ServerError : BooksResult()

}