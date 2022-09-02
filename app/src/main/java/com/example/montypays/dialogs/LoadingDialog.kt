package com.example.montypays.dialogs


import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import com.example.montypays.R


class LoadingDialog(
    requireActivity: Activity
) : Dialog(requireActivity) {

    private val wlp: WindowManager.LayoutParams = window!!.attributes
    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.loading_dialog)
        wlp.gravity = Gravity.CENTER
        wlp.flags = wlp.flags and WindowManager.LayoutParams.FLAG_BLUR_BEHIND.inv()
        window!!.attributes = wlp
        window!!.setBackgroundDrawableResource(R.drawable.loading)
        window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        window!!.decorView.systemUiVisibility = R.style.myFullscreenAlertDialogStyle
        show()
    }
}

//fun showDialog(activity: Activity?, msg: String?) {
//    val dialog = Dialog(activity!!, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen)
//    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//    dialog.setCancelable(false)
//    dialog.setContentView(com.example.montypays.R.layout.loading_dialog)
//
//    dialog.show()
//}