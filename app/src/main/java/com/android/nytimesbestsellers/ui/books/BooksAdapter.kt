package com.android.nytimesbestsellers.ui.books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.android.nytimesbestsellers.R
import com.android.nytimesbestsellers.data.model.Book
import com.android.nytimesbestsellers.extensions.navigateWithAnimationsBundle
import kotlinx.android.synthetic.main.item_book.view.*

class BooksAdapter(val books: List<Book>) : RecyclerView.Adapter<BooksAdapter.BooksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent,false)
        return BooksViewHolder(view, books)
    }

    override fun getItemCount(): Int = books.size

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder.bindView(books[position])
    }

    class BooksViewHolder(itemView: View, books: List<Book>) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener{
                var title = books[adapterPosition].title
                var author = books[adapterPosition].author
                var description = books[adapterPosition].description
                var bundle = bundleOf("title" to title, "author" to author, "description" to description)
                navigateDetail(itemView, bundle)
            }
        }

        private val title = itemView.book_title
        private val author = itemView.book_author

        fun bindView(book: Book) {
            title.text = book.title
            author.text = book.author
        }

        fun navigateDetail(itemView: View, bundle: Bundle) {
            itemView.findNavController().navigateWithAnimationsBundle(R.id.action_booksFragment_to_bookDetailsFragment, bundle)
        }
    }

}