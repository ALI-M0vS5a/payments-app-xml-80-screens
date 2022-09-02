package com.example.montypays.authentication.presentation.signup

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.montypays.databinding.RecyclerViewBottomSheetCompanySizeLayoutBinding
import com.example.montypays.dataclass.CompanySize

class CompanySizeRecyclerViewAdapter(
    val sizes: List<CompanySize>
) : RecyclerView.Adapter<CompanySizeRecyclerViewAdapter.RecyclerViewViewHolder>() {

    var selectedPosition = -1


    inner class RecyclerViewViewHolder(private val binding: RecyclerViewBottomSheetCompanySizeLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.textView.text = sizes[position].sizes
            binding.radioButton.isChecked = position == selectedPosition
            binding.radioButton.setOnClickListener {
                selectedPosition = absoluteAdapterPosition
                notifyItemRangeChanged(0, sizes.size)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewViewHolder {
        return RecyclerViewViewHolder(
            RecyclerViewBottomSheetCompanySizeLayoutBinding.inflate(
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
        return sizes.size
    }
}