package com.example.georgesamuel.kotlinapp.data.network

import com.example.georgesamuel.kotlinapp.data.network.responses.AuthResponse
import com.example.georgesamuel.kotlinapp.data.network.responses.QuoteResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface MyApi {

    @FormUrlEncoded
    @POST("course-apis/mvvm/login")
    suspend fun userLogin(@Field("email") email: String,
                  @Field("password") password: String): Response<AuthResponse>

    @FormUrlEncoded
    @POST("course-apis/mvvm/signup")
    suspend fun userSignup(@Field("name") name: String,
                           @Field("email") email: String,
                           @Field("password") password: String): Response<AuthResponse>

    @GET("course-apis/mvvm/quotes")
    suspend fun getQuotes(): Response<QuoteResponse>
}