package com.example.montypays.authentication.data

import android.content.SharedPreferences
import android.widget.TextView
import retrofit2.HttpException
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi,
    private val prefs: SharedPreferences,
    private val sharedPreferences: SharedPreferences
) : AuthRepository {

    override suspend fun signUp(
        fullName: String,
        workEmail: String,
        country: String,
        mobileNumber: String,
        companyName: String,
        website: String,
        password: String,
        companySize: String
    ): AuthResult<Unit> {
        return try {
            api.signUp(
                request = AuthRequestForSignUp(
                    fullName = fullName,
                    workEmail = workEmail,
                    country = country,
                    mobileNumber = mobileNumber,
                    companyName = companyName,
                    website = website,
                    password = password,
                    companySize = companySize
                )
            )
            signIn(workEmail, password)
        } catch (e: HttpException) {
            if (e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnKnownError()
            }
        } catch (e: Exception) {
            AuthResult.UnKnownError()
        }
    }

    override suspend fun signIn(workEmail: String, password: String): AuthResult<Unit> {
        return try {
            val  response = api.signIn(
                request = AuthRequest(
                    workEmail = workEmail,
                    password = password
                )
            )
            prefs.edit()
                .putString("jwt", response.data.authToken)
                .apply()
            sharedPreferences.edit()
                .putString("fullName",response.data.fullName)
                .putString("email",response.data.workEmail)
                .apply()
            AuthResult.Authorized()
        } catch (e: HttpException) {
            if (e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnKnownError()
            }
        } catch (e: Exception) {
            AuthResult.UnKnownError()
        }
    }

    override suspend fun authenticate(): AuthResult<Unit> {
        return try {
            val token = prefs.getString("jwt", null) ?: return AuthResult.Unauthorized()
            api.authenticate("Bearer $token")
            AuthResult.Authorized()
        } catch (e: HttpException) {
            if (e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnKnownError()
            }
        } catch (e: Exception) {
            AuthResult.UnKnownError()
        }
    }

    override suspend fun logout(): AuthResult<Unit> {
        return try {
            prefs.edit()
                .remove("jwt")
                .apply()
            sharedPreferences.edit()
                .remove("fullName")
                .remove("email")
                .apply()
            AuthResult.Unauthorized()
        } catch (e: Exception) {
            AuthResult.UnKnownError()
        }
    }
    override suspend fun getUser(fullName: TextView,workEmail: TextView){
         fullName.text = sharedPreferences.getString("fullName",null)
         workEmail.text = sharedPreferences.getString("email",null)
    }
}