package com.munidigital.bc2201.challengefinal.activities

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.munidigital.bc2201.challengefinal.databinding.ActivityLoginBinding
import com.munidigital.bc2201.challengefinal.ui.login.ViewModelLogin

class LoginActivity : AppCompatActivity() {
    private lateinit var viewModel: ViewModelLogin
    private lateinit var binding:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel=ViewModelProvider(this).get(ViewModelLogin::class.java)

        viewModel.session.observe(this) {
            if (it.session_result){
                startActivity(Intent(this, MainActivity::class.java))
            }
        }

        setup()

    }


    private fun setup() {
        val state_connection=verificateConnection()
        val user=binding.etUsr
        val passwd=binding.etPassword

        binding.btnLogin.setOnClickListener {
                viewModel.login(user.text.toString(), passwd.text.toString(),it.context,state_connection)
        }

        binding.btnNewAccount.setOnClickListener {
            viewModel.create(user.text.toString(), passwd.text.toString(),it.context,state_connection )

        }
    }

    override fun onStop() {
        super.onStop()
        finish()
    }

    fun verificateConnection():Boolean{
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork= cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

}