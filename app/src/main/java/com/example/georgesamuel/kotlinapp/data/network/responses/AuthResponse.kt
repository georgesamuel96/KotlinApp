package com.example.georgesamuel.kotlinapp.data.network.responses

import com.example.georgesamuel.kotlinapp.data.db.entities.User

data class AuthResponse(
    val isSuccessful : Boolean?,
    val message: String?,
    val user: User?
)