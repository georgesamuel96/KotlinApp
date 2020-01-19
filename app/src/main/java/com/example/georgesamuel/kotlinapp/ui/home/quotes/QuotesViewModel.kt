package com.example.georgesamuel.kotlinapp.ui.home.quotes

import androidx.lifecycle.ViewModel
import com.example.georgesamuel.kotlinapp.data.repositories.QuoteRepository
import com.example.georgesamuel.kotlinapp.util.lazyDeferred

class QuotesViewModel(repository: QuoteRepository) : ViewModel() {
    val quotes by lazyDeferred {
        repository.getQuotes()
    }
}
