package com.example.montypays.utils


import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.example.montypays.authentication.presentation.forgot.ForgotPasswordFragment
import com.example.montypays.authentication.presentation.login.LogInFragment
import com.example.montypays.authentication.presentation.signup.SignUpFragment
import com.example.montypays.authentication.presentation.onboard.OnBoardingFragment
import com.google.android.material.snackbar.Snackbar


fun LogInFragment.toast(message: String?) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}
fun SignUpFragment.toast(message: String?) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}
fun OnBoardingFragment.toast(message: String?) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}
fun ForgotPasswordFragment.toast(message: String?) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}
fun ProgressBar.show(){
    visibility = View.VISIBLE
}
fun ProgressBar.hide(){
    visibility = View.GONE
}
fun View.snackBar(message: String, action: (() -> Unit)? = null) {
    val snackBar = Snackbar.make(this,message,Snackbar.LENGTH_LONG)
    action?.let {
        snackBar.setAction("Retry") {
            it()
        }
    }
    snackBar.show()
}

fun <A : Activity> Activity.startNewActivity(activity: Class<A>){
    Intent(this,activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}



