package com.example.georgesamuel.kotlinapp.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.georgesamuel.kotlinapp.data.network.MyApi
import com.example.georgesamuel.kotlinapp.data.network.SafeApiRequest
import com.example.georgesamuel.kotlinapp.data.network.ServiceBuilder
import com.example.georgesamuel.kotlinapp.data.network.responses.AuthResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository : SafeApiRequest(){

    private var myApi: MyApi

    init {
        myApi = ServiceBuilder.buildService(MyApi::class.java)
    }

    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest{myApi.userLogin(email, password)}
    }
}