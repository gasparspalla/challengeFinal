package com.munidigital.bc2201.challengefinal.ui.Login

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.munidigital.bc2201.challengefinal.MainActivity
import com.munidigital.bc2201.challengefinal.R


class ViewModelLogin:ViewModel() {
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _session=MutableLiveData(Session(false,null,false,Session.ProviderType.NOTSESSION))
    val session : LiveData<Session>
        get() = _session

    init {
        val user = auth.currentUser

        if (user != null){
            _session.value = (Session(
                loginError = false,
                user = user,
                session_result = true,
                provider=Session.ProviderType.NOTSESSION
            ))
        }
    }

//    fun access(user:String,password:String){
//        if (user.isNotEmpty() && password.isNotEmpty()){
//            auth.createUserWithEmailAndPassword(user,password)
//                .addOnCompleteListener{
//                    if (it.isSuccessful){
//                        Session(
//                            loginError = false,
//                            createError=false,
//                            user = auth.currentUser,
//                            session_result = true,
//                            provider=Session.ProviderType.BASIC
//                        )
//
//                    }
//                    else{
//                        Session(
//                            loginError = false,
//                            createError = true,
//                            user = null,
//                            session_result = false,
//                            provider=Session.ProviderType.NOTSESSION
//                        )
//                    }
//                }
//        }
//    }

    fun login(user: String, password: String,context: Context) {
        if (user.isNotEmpty() && password.isNotEmpty()){
            auth.signInWithEmailAndPassword(user,password)
                .addOnCompleteListener{
                    if (it.isSuccessful){
                        Session(
                            loginError = false,
                            user = auth.currentUser,
                            session_result = true,
                            provider=Session.ProviderType.BASIC
                        )
                    }
                    else{
                        Session(
                            loginError = true,
                            user = null,
                            session_result = false,
                            provider=Session.ProviderType.NOTSESSION
                        )
                        showAlert(context)
                    }
                }
        }
    }

    fun showAlert(context: Context) {
        val builder= AlertDialog.Builder(context)
        builder.setTitle(context.getString(R.string.showAlertError))
        builder.setMessage(context.getString(R.string.MessaggeErrorLogin))
        builder.setPositiveButton("OK",null)
        val dialog: AlertDialog =builder.create()
        dialog.show()
    }

}