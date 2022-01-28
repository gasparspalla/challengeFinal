package com.munidigital.bc2201.challengefinal.activities

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelProvider
import com.munidigital.bc2201.challengefinal.databinding.ActivitySplashBinding
import com.munidigital.bc2201.challengefinal.ui.login.ViewModelLogin

class SplashActivity : AppCompatActivity() {
    private val SPLASH_DURATION:Long=2000
    private lateinit var binding: ActivitySplashBinding
    private lateinit var viewModel:ViewModelLogin
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySplashBinding.inflate(layoutInflater)

        setContentView(binding.root)
        viewModel= ViewModelProvider(this).get(ViewModelLogin::class.java)

        getAppVersion()
        val run= Runnable {

            viewModel.session.observe(this) { session ->
                if (session.session_result)startActivity(Intent(this, MainActivity::class.java))
                else startActivity(Intent(this, LoginActivity::class.java))
            }
            finish()
        }

        Handler(Looper.getMainLooper()).postDelayed(run, SPLASH_DURATION)
    }

    private fun getAppVersion() {
        try {
            val version = this.packageManager.getPackageInfo(this.packageName, 0).versionName
            binding.tvVersioname.text = version
        } catch (e: PackageManager.NameNotFoundException) {
            // Log por si falla
            e.printStackTrace()
        }
    }
}