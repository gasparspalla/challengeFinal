package com.munidigital.bc2201.challengefinal

import com.google.firebase.auth.FirebaseUser

data class Session(
    val loginError: Boolean,
    val user: FirebaseUser?,
    val session_result:Boolean)