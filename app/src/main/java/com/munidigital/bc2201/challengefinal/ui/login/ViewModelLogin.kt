package com.munidigital.bc2201.challengefinal.ui.login

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.munidigital.bc2201.challengefinal.R
import com.munidigital.bc2201.challengefinal.Session
import com.munidigital.bc2201.challengefinal.databinding.ActivityLoginBinding
import com.munidigital.bc2201.challengefinal.databinding.FragmentDetailBinding


class ViewModelLogin:ViewModel() {

    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    @SuppressLint("StaticFieldLeak")
    private lateinit var context: Context
    private lateinit var binding: ActivityLoginBinding
    private val _session = MutableLiveData(Session(false, null, false))
    val session: LiveData<Session>
        get() = _session

    init {
        val user = auth.currentUser

        if (user != null) {
            _session.value = (Session(
                loginError = false,
                user = user,
                session_result = true
            ))
        }
    }

    fun setContext(requireContext: Context) {
        this.context=requireContext
    }

    fun setBinding(binding: ActivityLoginBinding) {
        this.binding=binding
    }

    fun create(mail:String,pass:String,state_connection:Boolean){
        when(state_connection){
            true->{
                if (mail.isNotEmpty() && pass.isNotEmpty()){
                    auth.createUserWithEmailAndPassword(mail,pass)
                        .addOnCompleteListener{task->
                            if (task.isSuccessful) {
                                _session.value = Session(
                                    false,
                                    auth.currentUser,
                                    session_result = true
                                )
                            }
                            else {
                                _session.value = Session(
                                    true,
                                    null,
                                    session_result = false
                                )
                                showAlert(R.string.messageErrorAccount)
                            }
                        }
                }
                else if(mail.isBlank() || pass.isBlank()){
                    showAlert(R.string.messageErrorCreateAccount)
                }
            }
            false->showAlert(R.string.messaggeErrorConnectionFirebase)
        }
    }


    fun login(mail: String, pass: String,state_connection:Boolean) {
        when(state_connection){
            true->{
                if (mail.isNotBlank() && pass.isNotBlank()) {
                    auth.signInWithEmailAndPassword(mail, pass)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                _session.value = Session(
                                    false,
                                    auth.currentUser,
                                    session_result = true
                                )
                            } else {
                                _session.value = Session(
                                    true,
                                    null,
                                    session_result = false
                                )
                                showAlert(R.string.messaggeErrorLogin)
                            }
                        }
                }
                else if(mail.isBlank() || pass.isBlank()){
                    showAlert(R.string.messageErrorCreateAccount)

                }
            }
            false->showAlert(R.string.messaggeErrorConnectionFirebase)
        }

    }

    fun logout() {
        auth.signOut()
        _session.value = Session(
            false,
            null,
            false
        )
    }

    private fun showAlert(id:Int) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(context.getString(R.string.showAlertError))
        builder.setMessage(context.getString(id))
        builder.setPositiveButton("OK", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }




}






