package com.example.georgesamuel.kotlinapp.ui.home.quotes

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.georgesamuel.kotlinapp.R
import com.example.georgesamuel.kotlinapp.data.db.entities.Quote
import com.example.georgesamuel.kotlinapp.util.Coroutines
import kotlinx.android.synthetic.main.quotes_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class QuotesFragment : Fragment(), KodeinAware {

    private lateinit var viewModel: QuotesViewModel
    override val kodein by kodein()
    private val factory: QuotesViewModelFactory by instance()
    private lateinit var adapter: QuoteAdapter
    private var listQuotes: MutableList<Quote> = ArrayList()
    private lateinit var rvQuotes: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.quotes_fragment, container, false)

        initRv(view)
        viewModel = ViewModelProviders.of(this, factory).get(QuotesViewModel::class.java)
        Coroutines.main {
            val quotes = viewModel.quotes.await()
            quotes.observe(this, Observer{
                listQuotes.clear()
                listQuotes.addAll(it)
                adapter.notifyDataSetChanged()
            })
        }

        return view
    }

    private fun initRv(view: View) {
        adapter = QuoteAdapter(listQuotes)
        rvQuotes = view.findViewById(R.id.rv_quotes)
        rvQuotes.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvQuotes.setHasFixedSize(true)
        rvQuotes.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}
