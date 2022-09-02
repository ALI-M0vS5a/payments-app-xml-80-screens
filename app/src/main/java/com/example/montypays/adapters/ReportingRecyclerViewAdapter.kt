package com.example.montypays.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.montypays.databinding.RecyclerViewBottomSheetReportingLayoutBinding
import com.example.montypays.dataclass.Reporting

class ReportingRecyclerViewAdapter(
    val reporting: List<Reporting>
) : RecyclerView.Adapter<ReportingRecyclerViewAdapter.RecyclerViewViewHolder>() {
    var selectedPosition = -1
    inner class RecyclerViewViewHolder(private val binding: RecyclerViewBottomSheetReportingLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.textView.text = reporting[position].reporting
            binding.radioButton.isChecked = position == selectedPosition
            binding.radioButton.setOnClickListener {
                selectedPosition = absoluteAdapterPosition
                notifyItemRangeChanged(0,reporting.size)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewViewHolder {
        return RecyclerViewViewHolder(
            RecyclerViewBottomSheetReportingLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerViewViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return reporting.size
    }
}