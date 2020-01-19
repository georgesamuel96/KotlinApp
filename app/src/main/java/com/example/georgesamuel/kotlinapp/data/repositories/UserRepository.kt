package com.example.georgesamuel.kotlinapp.data.repositories

import com.example.georgesamuel.kotlinapp.data.db.AppDatabase
import com.example.georgesamuel.kotlinapp.data.db.entities.User
import com.example.georgesamuel.kotlinapp.data.network.MyApi
import com.example.georgesamuel.kotlinapp.data.network.SafeApiRequest
import com.example.georgesamuel.kotlinapp.data.network.responses.AuthResponse

class UserRepository(
    private val myApi: MyApi,
    private val db: AppDatabase
) : SafeApiRequest(){

    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest{myApi.userLogin(email, password)}
    }

    fun saveUser(user: User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getUser()
}