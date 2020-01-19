package com.example.georgesamuel.kotlinapp

import android.app.Application
import com.example.georgesamuel.kotlinapp.data.db.AppDatabase
import com.example.georgesamuel.kotlinapp.data.network.NetworkConnectionInterceptor
import com.example.georgesamuel.kotlinapp.data.network.ServiceBuilder
import com.example.georgesamuel.kotlinapp.data.repositories.QuoteRepository
import com.example.georgesamuel.kotlinapp.data.repositories.UserRepository
import com.example.georgesamuel.kotlinapp.ui.auth.AuthViewModelFactory
import com.example.georgesamuel.kotlinapp.ui.home.profile.ProfileViewModelFactory
import com.example.georgesamuel.kotlinapp.ui.home.quotes.QuotesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { ServiceBuilder(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { UserRepository(instance(), instance()) }
        bind() from singleton { QuoteRepository(instance(), instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }
        bind() from provider { QuotesViewModelFactory(instance()) }
    }
}