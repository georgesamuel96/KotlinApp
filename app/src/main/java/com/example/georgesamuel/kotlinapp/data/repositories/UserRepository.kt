package com.example.georgesamuel.kotlinapp.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.georgesamuel.kotlinapp.data.network.MyApi
import com.example.georgesamuel.kotlinapp.data.network.ServiceBuilder
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {

    private var myApi: MyApi

    init {
        myApi = ServiceBuilder.buildService(MyApi::class.java)
    }

    fun userLogin(email: String, password: String): LiveData<String> {
        val loginResponse = MutableLiveData<String>()
        myApi.userLogin(email, password).enqueue(object : Callback<RequestBody>{
            override fun onFailure(call: Call<RequestBody>, t: Throwable) {
                loginResponse.value = t.message
            }

            override fun onResponse(call: Call<RequestBody>, response: Response<RequestBody>) {
                if(response.isSuccessful){
                    loginResponse.value = response.body()?.toString()
                }
                else {
                    loginResponse.value = response.errorBody()?.toString()
                }
            }
        })
        return loginResponse
    }
}