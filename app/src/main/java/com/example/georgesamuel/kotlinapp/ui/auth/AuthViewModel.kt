package com.example.georgesamuel.kotlinapp.ui.auth

import androidx.lifecycle.ViewModel
import com.example.georgesamuel.kotlinapp.data.repositories.UserRepository
import com.example.georgesamuel.kotlinapp.util.ApiException
import com.example.georgesamuel.kotlinapp.util.Coroutines

class AuthViewModel : ViewModel() {

    var authListener: AuthListener? = null

    fun onLoginButtonClick(email: String, password: String) {
        authListener?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailure("Invalid email or password")
            return
        }
        Coroutines.main{
            try {
                val authResponse = UserRepository().userLogin(email, password)
                authResponse?.let {
                    authListener?.onSuccess(it.user!!)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            } catch (e: ApiException) {
                authListener?.onFailure(e.message!!)
            }
        }
    }
}