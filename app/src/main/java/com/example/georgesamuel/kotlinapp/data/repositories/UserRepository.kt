package com.example.georgesamuel.kotlinapp.data.repositories

import com.example.georgesamuel.kotlinapp.data.db.AppDatabase
import com.example.georgesamuel.kotlinapp.data.db.entities.User
import com.example.georgesamuel.kotlinapp.data.network.MyApi
import com.example.georgesamuel.kotlinapp.data.network.SafeApiRequest
import com.example.georgesamuel.kotlinapp.data.network.ServiceBuilder
import com.example.georgesamuel.kotlinapp.data.network.responses.AuthResponse

class UserRepository(
    serviceBuilder: ServiceBuilder,
    private val db: AppDatabase
) : SafeApiRequest(){

    private val myApi = serviceBuilder.buildService(MyApi::class.java)

    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest{myApi.userLogin(email, password)}
    }

    suspend fun userSignup(name: String, email: String, password: String): AuthResponse {
        return apiRequest{myApi.userSignup(name, email, password)}
    }
    fun saveUser(user: User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getUser()
}