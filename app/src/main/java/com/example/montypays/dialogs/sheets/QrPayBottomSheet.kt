package com.example.montypays.dialogs.sheets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.montypays.adapters.QrPayRecyclerViewAdapter
import com.example.montypays.databinding.QrPayBottomSheetLayoutBinding
import com.example.montypays.dataclass.Merchant
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * Created by Ali Moussa on $(DATE).
 */
class QrPayBottomSheet: BottomSheetDialogFragment() {

    private lateinit var binding: QrPayBottomSheetLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = QrPayBottomSheetLayoutBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val merchants  = mutableListOf(
            Merchant(
                "Merchant 1"
            ),
            Merchant(
                "Merchant 2"
            ),
            Merchant(
                "Merchant 3"
            ),
            Merchant(
                "Merchant 4"
            ),
        )
        val adapter = QrPayRecyclerViewAdapter(merchants)
        binding.rvMerchants.adapter = adapter

        binding.closeSheet.setOnClickListener {
            dismiss()
        }
    }
}