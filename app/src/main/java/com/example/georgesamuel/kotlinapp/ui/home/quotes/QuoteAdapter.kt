package com.example.georgesamuel.kotlinapp.ui.home.quotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.georgesamuel.kotlinapp.R
import com.example.georgesamuel.kotlinapp.data.db.entities.Quote
import kotlinx.android.synthetic.main.item_quote.view.*

class QuoteAdapter(private val list: List<Quote>) : RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_quote, parent, false)
        return QuoteViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val quote = list[position]
        holder.author.text = quote.author
        holder.quote.text = quote.quote
    }

    class QuoteViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val quote = view.quote
        val author = view.author
    }
}