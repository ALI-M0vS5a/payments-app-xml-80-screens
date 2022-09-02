package com.example.montypays.authentication.data

import android.widget.TextView

interface AuthRepository {
    suspend fun signUp(
        fullName: String,
        workEmail: String,
        country: String,
        mobileNumber: String,
        companyName: String,
        website: String,
        password: String,
        companySize: String
    ): AuthResult<Unit>

    suspend fun signIn(workEmail: String, password: String): AuthResult<Unit>
    suspend fun authenticate(): AuthResult<Unit>
    suspend fun logout(): AuthResult<Unit>
    suspend fun getUser(fullName: TextView, workEmail: TextView)
}