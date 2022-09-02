package com.example.montypays.dialogs


import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.example.montypays.R
import com.example.montypays.authentication.presentation.AuthenticationViewModel
import com.example.montypays.home.SettingsFragment


class LogoutDialog(
    context: Context,
    requireActivity: Activity,
    viewModel: AuthenticationViewModel,
    fragment: SettingsFragment
) : AlertDialog(requireActivity) {

    var promptView: View = layoutInflater.inflate(R.layout.dialog_logout, null)

    init {
        val alertDialog = Builder(context, R.style.CustomAlertDialog)
            .setView(promptView)
            .setCancelable(false)
            .show()

        val logout = promptView.findViewById<TextView>(R.id.logout)
        logout.setOnClickListener {
            LoadingDialog(requireActivity).show()
            viewModel.logout()
            fragment.findNavController().navigate(R.id.action_settingsFragment_to_mainActivity)
        }
        val cancel = promptView.findViewById<TextView>(R.id.cancel)
        cancel.setOnClickListener {
            alertDialog.dismiss()
        }
    }
}
