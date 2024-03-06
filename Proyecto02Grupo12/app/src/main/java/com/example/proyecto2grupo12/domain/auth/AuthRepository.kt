package com.example.proyecto2grupo12.domain.auth

import android.content.Intent
import com.example.proyecto2grupo12.data.datasource.SignInCheck
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    val isUserSignedIn: Boolean
    val userId: String
    suspend fun signInWithGoogle(data: Intent?, signInCheck: SignInCheck = SignInCheck.ALL_USERS): FirebaseUser
    fun signOut()
}