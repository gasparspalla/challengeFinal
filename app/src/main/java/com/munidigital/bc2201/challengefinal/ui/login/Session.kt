package com.munidigital.bc2201.challengefinal.ui.login

import com.google.firebase.auth.FirebaseUser

data class Session(
    val loginError: Boolean,
    val user: FirebaseUser?,
    val session_result:Boolean)