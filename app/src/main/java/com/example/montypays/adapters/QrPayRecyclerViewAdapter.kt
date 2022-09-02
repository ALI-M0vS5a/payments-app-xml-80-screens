package com.example.montypays.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.montypays.databinding.RecyclerViewBottomSheetQrPayLayoutBinding
import com.example.montypays.dataclass.Merchant


/**
 * Created by Ali Moussa on $(DATE).
 */
class QrPayRecyclerViewAdapter(
    val merchants: List<Merchant>
) : RecyclerView.Adapter<QrPayRecyclerViewAdapter.RecyclerViewViewHolder>() {
    var selectedPosition = -1

    inner class RecyclerViewViewHolder(private val binding: RecyclerViewBottomSheetQrPayLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.textView.text = merchants[position].filter
            binding.radioButton.isChecked = position == selectedPosition
            binding.radioButton.setOnClickListener {
                selectedPosition = absoluteAdapterPosition
                notifyItemRangeChanged(0, merchants.size)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewViewHolder {
        return RecyclerViewViewHolder(
            RecyclerViewBottomSheetQrPayLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerViewViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return merchants.size
    }
}