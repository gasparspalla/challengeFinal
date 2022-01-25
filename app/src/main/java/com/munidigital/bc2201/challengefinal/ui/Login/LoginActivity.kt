package com.munidigital.bc2201.challengefinal.ui.Login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.munidigital.bc2201.challengefinal.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel:ViewModelLogin
    private lateinit var session:Session

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel= ViewModelProvider(this).get(ViewModelLogin::class.java)


        viewModel.session.observe(this) {
            session=it
        }

        setup()
    }
    private fun setup() {
        val user=binding.etUsr
        val password=binding.etPassword
        binding.btnLogin.setOnClickListener {
            viewModel.login(user.text.toString(), password.text.toString(),it.context)
        }
    }
}