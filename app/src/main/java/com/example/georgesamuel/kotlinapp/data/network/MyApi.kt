package com.example.georgesamuel.kotlinapp.data.network

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MyApi {

    @FormUrlEncoded
    @POST("course-apis/mvvm/login")
    fun userLogin(@Field("email") email: String,
                  @Field("password") password: String): Call<RequestBody>
}