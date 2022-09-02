package com.example.montypays.dialogs

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.example.montypays.R


class InternetConnectionDialog(
    context: Context,
    requireActivity: Activity
) : AlertDialog(requireActivity) {

    private var promptView: View = layoutInflater.inflate(R.layout.dialog_internet_connection,null)

    init {
        val alertDialog = Builder(context,R.style.CustomAlertDialogInternet)
            .setView(promptView)
            .setCancelable(true)
            .show()


        val close = promptView.findViewById<ImageButton>(R.id.close)
        close.setOnClickListener {
            alertDialog.dismiss()
        }

        val tryAgain = promptView.findViewById<Button>(R.id.bt_try_again)
        tryAgain.setOnClickListener {
            Toast.makeText(requireActivity, "tryAgain", Toast.LENGTH_SHORT).show()
        }
    }
}