package com.example.georgesamuel.kotlinapp.ui.home.profile

import androidx.lifecycle.ViewModel
import com.example.georgesamuel.kotlinapp.data.repositories.UserRepository

class ProfileViewModel(repository: UserRepository): ViewModel() {
    val user = repository.getUser()
}