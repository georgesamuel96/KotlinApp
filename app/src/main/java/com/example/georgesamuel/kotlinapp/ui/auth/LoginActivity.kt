package com.example.georgesamuel.kotlinapp.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.georgesamuel.kotlinapp.R
import com.example.georgesamuel.kotlinapp.data.db.entities.User
import com.example.georgesamuel.kotlinapp.util.hide
import com.example.georgesamuel.kotlinapp.util.show
import com.example.georgesamuel.kotlinapp.util.snackbar
import com.example.georgesamuel.kotlinapp.util.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthListener {

    lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        viewModel.authListener = this
    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(user: User) {
        progress_bar.hide()
        root_layout.snackbar("${user.name} is Logged In")
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        root_layout.snackbar(message)
    }

    fun onLoginButtonClick(view: View) {
        val email:String = et_email.text.toString()
        val password: String = et_password.text.toString()
        viewModel.onLoginButtonClick(email, password)
    }
}
