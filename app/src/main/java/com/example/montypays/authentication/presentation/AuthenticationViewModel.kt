package com.example.montypays.authentication.presentation


import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.montypays.authentication.data.AuthRepository
import com.example.montypays.authentication.data.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _response: MutableLiveData<AuthResult<Unit>> = MutableLiveData()
    val response: LiveData<AuthResult<Unit>>
        get() = _response

    init {
        authenticate()
    }

    fun login(
        workEmail: String, password: String
    ) = viewModelScope.launch {
        _response.value = repository.signIn(
            workEmail = workEmail,
            password = password
        )
    }

    fun signUp(
        fullName: String,
        workEmail: String,
        country: String,
        mobileNumber: String,
        companyName: String,
        website: String,
        password: String,
        companySize: String
    ) = viewModelScope.launch {
        _response.value = repository.signUp(
            fullName = fullName,
            workEmail = workEmail,
            country = country,
            mobileNumber = mobileNumber,
            companyName = companyName,
            website = website,
            password = password,
            companySize = companySize
        )
    }

    private fun authenticate() {
        viewModelScope.launch {
            _response.value = repository.authenticate()
        }
    }

    fun logout() {
        viewModelScope.launch {
            _response.value = repository.logout()
        }
    }
    fun getUser(fullName: TextView, workEmail: TextView){
        viewModelScope.launch {
            repository.getUser(fullName,workEmail)
        }
    }
}


