package com.example.georgesamuel.kotlinapp.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.georgesamuel.kotlinapp.R
import com.example.georgesamuel.kotlinapp.data.db.entities.User
import com.example.georgesamuel.kotlinapp.ui.home.HomeActivity
import com.example.georgesamuel.kotlinapp.util.*
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), AuthListener, KodeinAware {

    lateinit var viewModel: AuthViewModel
    override val kodein by kodein()
    private val factory: AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        viewModel.authListener = this
        viewModel.getLoggedInUser().observe(this, Observer { user ->
                if (user != null) {
                    Intent(this, HomeActivity::class.java).also {
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(it)
                    }
                }
        })
    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(user: User) {
        progress_bar.hide()
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
