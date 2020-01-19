package com.example.georgesamuel.kotlinapp.ui.home.quotes

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.example.georgesamuel.kotlinapp.R
import com.example.georgesamuel.kotlinapp.util.Coroutines
import com.example.georgesamuel.kotlinapp.util.toast
import okhttp3.internal.wait
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class QuotesFragment : Fragment(), KodeinAware {

    private lateinit var viewModel: QuotesViewModel
    override val kodein by kodein()
    private val factory: QuotesViewModelFactory by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.quotes_fragment, container, false)

        viewModel = ViewModelProviders.of(this, factory).get(QuotesViewModel::class.java)
        Coroutines.main {
            val quotes = viewModel.quotes.await()
            quotes.observe(this, Observer{
                context?.toast(it.size.toString())
            })
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}
