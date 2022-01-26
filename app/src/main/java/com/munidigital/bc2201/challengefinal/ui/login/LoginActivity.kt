package com.munidigital.bc2201.challengefinal.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.munidigital.bc2201.challengefinal.MainActivity
import com.munidigital.bc2201.challengefinal.R
import com.munidigital.bc2201.challengefinal.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var viewModel:ViewModelLogin
    private lateinit var binding:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel=ViewModelProvider(this).get(ViewModelLogin::class.java)

        viewModel.session.observe(this) {
            if (it.session_result){
                startActivity()
            }
        }

        setup()

    }

    private fun startActivity() {
        startActivity(Intent(this, MainActivity::class.java))

    }

    private fun setup() {
        val user=binding.etUsr
        val passwd=binding.etPassword

        binding.btnLogin.setOnClickListener {

            viewModel.login(user.text.toString(), passwd.text.toString(),it.context)
        }
    }

    override fun onStop() {
        super.onStop()
        finish()
    }
}