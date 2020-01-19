package com.example.georgesamuel.kotlinapp.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.georgesamuel.kotlinapp.data.db.AppDatabase
import com.example.georgesamuel.kotlinapp.data.db.entities.Quote
import com.example.georgesamuel.kotlinapp.data.network.MyApi
import com.example.georgesamuel.kotlinapp.data.network.SafeApiRequest
import com.example.georgesamuel.kotlinapp.data.network.ServiceBuilder
import com.example.georgesamuel.kotlinapp.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuoteRepository(serviceBuilder: ServiceBuilder,
                      private val db: AppDatabase): SafeApiRequest() {

    private val quotes = MutableLiveData<List<Quote>>()
    private val myApi = serviceBuilder.buildService(MyApi::class.java)

    init {
        quotes.observeForever{
            saveQuotes(it)
        }
    }

    private fun saveQuotes(quotes: List<Quote>) {
        Coroutines.io {
            db.getQuoteDao().saveAllQuotes(quotes)
        }
    }

    private suspend fun fetchQuotes(){
        if(isFetchNeeded()){
            val response = apiRequest { myApi.getQuotes() }
            quotes.postValue(response.quotes)
        }
    }

    suspend fun getQuotes(): LiveData<List<Quote>>{
        return withContext(Dispatchers.IO){
            fetchQuotes()
            db.getQuoteDao().getQuotes()
        }
    }

    private fun isFetchNeeded(): Boolean{
        return true
    }
}