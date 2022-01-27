package com.munidigital.bc2201.challengefinal.ui.login

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthActionCodeException
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.munidigital.bc2201.challengefinal.R


class ViewModelLogin:ViewModel() {

    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

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

    fun create(mail:String,pass:String,context: Context,state_connection:Boolean){
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
                            } else {
                                _session.value = Session(
                                    true,
                                    null,
                                    session_result = false
                                )
                                showAlertAccountAlreadyExists(context)
                            }
                        }
                }
                else if(mail.isBlank() || pass.isBlank()){
                    showAlertEmpty(context)
                }
            }
            false->showAlertNoConnection(context)
        }
    }


    fun login(mail: String, pass: String,context: Context,state_connection:Boolean) {
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
                                showAlertIncorrectLogin(context)
                            }
                        }
                }
                else if(mail.isBlank() || pass.isBlank()){
                    showAlertEmpty(context)

                }
            }
            false->showAlertNoConnection(context)
        }

    }

    private fun showAlertNoConnection(context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(context.getString(R.string.showAlertError))
        builder.setMessage(context.getString(R.string.messaggeErrorConnection))
        builder.setPositiveButton("OK", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


    fun logout() {
            auth.signOut()
            _session.value = Session(
                false,
                null,
                false
            )
        }

    private fun showAlertIncorrectLogin(context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(context.getString(R.string.showAlertError))
        builder.setMessage(context.getString(R.string.messaggeErrorLogin))
        builder.setPositiveButton("OK", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showAlertEmpty(context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(context.getString(R.string.showAlertError))
        builder.setMessage(context.getString(R.string.messageErrorCreateAccount))
        builder.setPositiveButton("OK", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


    private fun showAlertAccountAlreadyExists(context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(context.getString(R.string.showAlertError))
        builder.setMessage(context.getString(R.string.messageErrorAccountAlreadyExist))
        builder.setPositiveButton("OK", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}






