package com.example.georgesamuel.kotlinapp.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ServiceBuilder {

    companion object {
        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor): MyApi {
            val BASE_URL = "https://api.simplifiedcoding.in/"
            val logger = run {
                val httpLoggingInterceptor = HttpLoggingInterceptor()
                httpLoggingInterceptor.apply {
                    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                }
            }

            val okHttp = OkHttpClient.Builder()
                .callTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(logger)
                .addInterceptor(networkConnectionInterceptor)
            val builder =
                Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttp.build())
            val retrofit = builder.build()
            return retrofit.create(MyApi::class.java)
        }
    }

//    private val BASE_URL = "https://api.simplifiedcoding.in/"
//    private val logger = run {
//        val httpLoggingInterceptor = HttpLoggingInterceptor()
//        httpLoggingInterceptor.apply {
//            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//        }
//    }
//
//    private val okHttp = OkHttpClient.Builder()
//        .callTimeout(5, TimeUnit.SECONDS)
//        .addInterceptor(logger)
//        .addInterceptor(NetworkConnectionInterceptor(Utils.context!!))
//    private val builder =
//        Retrofit.Builder().baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(okHttp.build())
//    private val retrofit = builder.build()
//
//    fun <T> buildService(serviceType: Class<T>): T {
//        return retrofit.create(serviceType)
//    }
}