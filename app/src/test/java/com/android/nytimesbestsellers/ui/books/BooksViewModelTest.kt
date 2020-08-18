package com.android.nytimesbestsellers.ui.books

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.android.nytimesbestsellers.R
import com.android.nytimesbestsellers.data.BooksResult
import com.android.nytimesbestsellers.data.model.Book
import com.android.nytimesbestsellers.data.repository.BooksRepository
import com.nhaarman.mockitokotlin2.verify
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BooksViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var booksLiveDataObserver: Observer<List<Book>>

    @Mock
    private lateinit var viewFlipperLiveDataObserver: Observer<Pair<Int, Int?>>

    private lateinit var viewModel: BooksViewModel

    @Test
    fun `when viewModel getBooks get sucess then sets bookLiveData` () {
        // Arrange
        val books = listOf(
            Book("Título 1", "Autor 1", "Descrição 1")
        )
        val resultSucess = MockRepository(BooksResult.Sucess(books))
        viewModel = BooksViewModel(resultSucess)
        viewModel.booksLiveData.observeForever(booksLiveDataObserver)
        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        // Act
        viewModel.getBooks()

        // Assert
        verify(booksLiveDataObserver).onChanged(books)
        verify(viewFlipperLiveDataObserver).onChanged(Pair(1, null))

    }

    @Test
    fun `when viewModel getBooks get server error then sets viewFlipperLiveData` () {
        // Arrange
        val resultServerError = MockRepository(BooksResult.ServerError)
        viewModel = BooksViewModel(resultServerError)
        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        // Act
        viewModel.getBooks()

        // Assert
        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.error_500_generic))
    }

}

class MockRepository(private val result: BooksResult) : BooksRepository {

    override fun getBooks(booksResultCallback: (result: BooksResult) -> Unit) {
        booksResultCallback(result)
    }

}