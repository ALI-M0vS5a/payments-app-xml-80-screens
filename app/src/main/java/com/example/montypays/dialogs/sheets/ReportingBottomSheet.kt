package com.example.montypays.dialogs.sheets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.montypays.adapters.ReportingRecyclerViewAdapter
import com.example.montypays.databinding.ReportingBottomSheetLayoutBinding
import com.example.montypays.dataclass.Reporting
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class ReportingBottomSheet: BottomSheetDialogFragment() {
    private lateinit var binding: ReportingBottomSheetLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ReportingBottomSheetLayoutBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val reporting = mutableListOf(

            Reporting(
                "Sales by Currency"
            ),
            Reporting(
                "Sales Transaction Count"
            ),
            Reporting(
                "Refund"
            ),
            Reporting(
                "Chargeback"
            ),
            Reporting(
                "Payment Trend"
            ),
            Reporting(
                "Sales by Countries"
            )
        )
        val adapter = ReportingRecyclerViewAdapter(reporting)
        binding.rvReporting.adapter = adapter

        binding.closeSheet.setOnClickListener {
            dismiss()
        }
    }
}