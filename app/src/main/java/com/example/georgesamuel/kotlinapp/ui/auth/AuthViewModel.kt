package com.example.georgesamuel.kotlinapp.ui.auth

import androidx.lifecycle.ViewModel
import com.example.georgesamuel.kotlinapp.data.repositories.UserRepository

class AuthViewModel : ViewModel() {

    var authListener: AuthListener? = null

    fun onLoginButtonClick(email: String, password: String) {
        authListener?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailure("Invalid email or password")
            return
        }
        val loginResponse = UserRepository().userLogin(email, password)
        authListener!!.onSuccess(loginResponse)
    }
}