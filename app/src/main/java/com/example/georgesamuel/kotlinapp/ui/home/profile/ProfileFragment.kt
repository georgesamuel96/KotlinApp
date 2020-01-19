package com.example.georgesamuel.kotlinapp.ui.home.profile


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.georgesamuel.kotlinapp.R
import kotlinx.android.synthetic.main.fragment_profile.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment(), KodeinAware {

    private lateinit var viewModel: ProfileViewModel
    override val kodein by kodein()
    private val factory: ProfileViewModelFactory by instance()

    companion object{
        fun newInstance() = ProfileFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        viewModel = ViewModelProviders.of(this, factory).get(ProfileViewModel::class.java)
        viewModel.user.observe(this, Observer {
            tv_name.text = it.name
            tv_email.text = it.email
        })
        return view
    }
}
