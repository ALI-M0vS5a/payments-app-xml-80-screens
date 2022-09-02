package com.example.montypays.dialogs

import android.app.Activity
import android.app.AlertDialog
import android.view.View
import android.widget.DatePicker
import com.example.montypays.R
import org.joda.time.DateTime


class MyDatePickerDialog(
    requireActivity: Activity
) : AlertDialog(requireActivity) {
    var promptView: View = layoutInflater.inflate(R.layout.dialog_date_picker, null)

    private var listener: OnOkClicked? = null

    interface OnOkClicked {
        fun onClick(item: String?)
    }

    fun setListener(listener: OnOkClicked?) {
        this.listener = listener
    }

    init {
        Builder(context)
            .setView(promptView)
            .setCancelable(false)
            .setPositiveButton("OK") { _, _ -> dialogConfirmed() }
            .setNegativeButton("Cancel", null)
            .show()

    }

    private fun dialogConfirmed() {
        val year = promptView.findViewById<DatePicker>(R.id.date_picker).year
        val month = promptView.findViewById<DatePicker>(R.id.date_picker).month
        val day = promptView.findViewById<DatePicker>(R.id.date_picker).dayOfMonth
        val date = DateTime().withDate(year, month, day).withTimeAtStartOfDay()

        listener!!.onClick(date.toString("d MMM yyyy"))
        this.dismiss()

    }
}