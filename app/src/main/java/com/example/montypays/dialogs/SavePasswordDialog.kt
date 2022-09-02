package com.example.montypays.dialogs

import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.example.montypays.databinding.SavingPasswordDialogBinding
import com.example.montypays.home.HomeActivity
import com.example.montypays.utils.startNewActivity


class SavePasswordDialog : DialogFragment() {

    private lateinit var binding: SavingPasswordDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SavingPasswordDialogBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btSave.setOnClickListener {
            requireActivity()
                .startNewActivity(HomeActivity::class.java)
        }
        binding.btNot.setOnClickListener {
            requireActivity()
                .startNewActivity(HomeActivity::class.java)
        }
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window!!.setLayout(width, height)
            dialog.window!!.decorView.systemUiVisibility =
                android.R.style.Theme_Translucent_NoTitleBar_Fullscreen
            dialog.window!!.setBackgroundDrawableResource(com.google.android.material.R.drawable.m3_tabs_transparent_background)
            dialog.window!!.decorView.setBackgroundColor(android.graphics.Color.TRANSPARENT)

        }
    }
}

