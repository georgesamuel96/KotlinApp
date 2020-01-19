package com.example.georgesamuel.kotlinapp.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.georgesamuel.kotlinapp.R
import com.example.georgesamuel.kotlinapp.data.db.entities.User
import com.example.georgesamuel.kotlinapp.ui.home.HomeActivity
import com.example.georgesamuel.kotlinapp.util.hide
import com.example.georgesamuel.kotlinapp.util.show
import com.example.georgesamuel.kotlinapp.util.snackbar
import kotlinx.android.synthetic.main.activity_signup.progress_bar
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignupActivity : AppCompatActivity(), AuthListener, KodeinAware {

    lateinit var viewModel: AuthViewModel
    override val kodein by kodein()
    private val factory: AuthViewModelFactory by instance()

    lateinit var etEmail: EditText
    lateinit var etPassword: EditText
    lateinit var etName: EditText
    lateinit var etConfirmPass: EditText
    lateinit var rootLayout: CoordinatorLayout
    lateinit var tvHaveAccount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        init()
        tvHaveAccount.setOnClickListener {finish()}

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

    private fun init() {
        etEmail = findViewById(R.id.et_email)
        etName = findViewById(R.id.et_name)
        etPassword = findViewById(R.id.et_password)
        etConfirmPass = findViewById(R.id.et_confirm_pass)
        rootLayout = findViewById(R.id.root_layout)
        tvHaveAccount = findViewById(R.id.tv_have_account)
    }

    fun onSignupButtonClick(view: View) {
        val name = etName.text.toString()
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()
        val confirmPass = etConfirmPass.text.toString()
        viewModel.onSignupButtonClick(name, email, password, confirmPass)
    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(user: User) {
        progress_bar.hide()
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        rootLayout.snackbar(message)
    }
}
