package com.example.georgesamuel.kotlinapp.ui.auth

import androidx.lifecycle.ViewModel
import com.example.georgesamuel.kotlinapp.data.repositories.UserRepository
import com.example.georgesamuel.kotlinapp.util.ApiException
import com.example.georgesamuel.kotlinapp.util.Coroutines
import com.example.georgesamuel.kotlinapp.util.NoInternetException

class AuthViewModel(
    private val repository: UserRepository
) : ViewModel() {

    var authListener: AuthListener? = null

    fun onLoginButtonClick(email: String, password: String) {
        authListener?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailure("Invalid email or password")
            return
        }
        Coroutines.main{
            try {
                val authResponse = repository.userLogin(email, password)
                authResponse?.let {
                    authListener?.onSuccess(it.user!!)
                    repository.saveUser(it.user!!)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            } catch (e: ApiException) {
                authListener?.onFailure(e.message!!)
            } catch (e: NoInternetException) {
                authListener?.onFailure(e.message!!)
            }
        }
    }

    fun getLoggedInUser() = repository.getUser()
}