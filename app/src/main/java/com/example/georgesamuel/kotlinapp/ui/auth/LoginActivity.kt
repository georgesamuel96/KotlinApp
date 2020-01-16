package com.example.georgesamuel.kotlinapp.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.lifecycle.ViewModelProviders
import com.example.georgesamuel.kotlinapp.R
import com.example.georgesamuel.kotlinapp.util.toast

class LoginActivity : AppCompatActivity(), AuthListener {

    lateinit var viewModel: AuthViewModel
    lateinit var etEmail: EditText
    lateinit var etPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)

        viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        viewModel.authListener = this
    }

    override fun onStarted() {
        toast("Login Started")
    }

    override fun onSuccess() {
        toast("Login Success")
    }

    override fun onFailure(message: String) {
        toast(message)
    }

    fun onLoginButtonClick(view: View) {
        val email:String = etEmail.text.toString()
        val password: String = etPassword.text.toString()
        viewModel.onLoginButtonClick(email, password)
    }
}
