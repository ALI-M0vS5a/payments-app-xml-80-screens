package com.example.montypays.authentication.data

data class Data(
    val authToken: String,
    val companyName: String,
    val companySize: String,
    val country: String,
    val createdAt: String,
    val fullName: String,
    val id: Int,
    val mobileNumber: String,
    val website: String,
    val workEmail: String
)