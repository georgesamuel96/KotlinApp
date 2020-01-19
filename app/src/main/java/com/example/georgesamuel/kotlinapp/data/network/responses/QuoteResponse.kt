package com.example.georgesamuel.kotlinapp.data.network.responses

import com.example.georgesamuel.kotlinapp.data.db.entities.Quote

data class QuoteResponse(val isSuccessful: String,
                         val quotes: List<Quote>) {
}