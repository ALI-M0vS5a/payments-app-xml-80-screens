package com.example.montypays.authentication.data

data class AuthRequestForSignUp(
    val fullName: String,
    val workEmail: String,
    val country: String,
    val mobileNumber: String,
    val companyName: String,
    val website: String,
    val password: String,
    val companySize: String
)
