package com.example.montypays.dialogs.sheets


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.montypays.authentication.presentation.signup.CompanySizeRecyclerViewAdapter
import com.example.montypays.databinding.BottomSheetCompanySizeLayoutBinding
import com.example.montypays.dataclass.CompanySize
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.lang.Exception


class CompanySizeBottomSheet : BottomSheetDialogFragment() {
    private lateinit var binding: BottomSheetCompanySizeLayoutBinding


    private var listener: OnApplyButtonClicked? = null
    interface OnApplyButtonClicked{
        fun onClick(item: String?)
    }

    fun setListener(listener: OnApplyButtonClicked?) {
        this.listener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetCompanySizeLayoutBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val companySizes = mutableListOf(
            CompanySize(
                "Large Company"
            ),
            CompanySize(
                "Small Company"
            ),
            CompanySize(
                "SDE"
            )
        )
        val adapter = CompanySizeRecyclerViewAdapter(companySizes)
        binding.rvBottomSheet.adapter = adapter

        binding.btApply.setOnClickListener {
            try {
                val text: String = adapter.sizes[adapter.selectedPosition].sizes
                listener!!.onClick(text)
                this.dismiss()
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Please Select size", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
