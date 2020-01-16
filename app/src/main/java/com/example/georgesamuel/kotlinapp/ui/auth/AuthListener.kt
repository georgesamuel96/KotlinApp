package com.example.georgesamuel.kotlinapp.ui.auth

import androidx.lifecycle.LiveData
import com.example.georgesamuel.kotlinapp.data.db.entities.User

interface AuthListener {
    fun onStarted()
    fun onSuccess(user: User)
    fun onFailure(message: String)
}