package com.example.georgesamuel.kotlinapp.ui.auth

import androidx.lifecycle.ViewModel
import com.example.georgesamuel.kotlinapp.data.repositories.UserRepository
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
            val response = UserRepository().userLogin(email, password)
            if(response.isSuccessful){
                authListener?.onSuccess(response.body()?.user!!)
            }
            else {
                authListener?.onFailure("Error Code: ${response.code()}")
            }
        }
    }
}