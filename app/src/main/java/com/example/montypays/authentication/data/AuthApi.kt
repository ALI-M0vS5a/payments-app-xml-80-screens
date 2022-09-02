package com.example.montypays.authentication.data

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApi {

    @POST("register")
    suspend fun signUp(
        @Body request: AuthRequestForSignUp
    )

    @POST("login")
    suspend fun signIn(
        @Body request: AuthRequest
    ): LoginResponse

    @GET("authenticate")
    suspend fun authenticate(
        @Header("Authorization") token: String
    )
}