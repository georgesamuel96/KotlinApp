package com.example.georgesamuel.kotlinapp.ui.home.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.georgesamuel.kotlinapp.data.repositories.QuoteRepository
import com.example.georgesamuel.kotlinapp.data.repositories.UserRepository

class QuotesViewModelFactory(private val repository: QuoteRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuotesViewModel(repository) as T
    }
}