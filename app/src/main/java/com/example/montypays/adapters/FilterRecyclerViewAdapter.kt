package com.example.montypays.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.montypays.databinding.RecyclerViewBottomSheetFilterLayoutBinding
import com.example.montypays.dataclass.Filter

/**
 * Created by Ali Moussa on $(DATE).
 */
class FilterRecyclerViewAdapter(
    val items: List<Filter>
) : RecyclerView.Adapter<FilterRecyclerViewAdapter.RecyclerViewViewHolder>() {
    private lateinit var mListener: OnItemClickListener

    var selectedPosition = 0

    interface OnItemClickListener {
        fun onItemClicked(filter: Filter, position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.mListener = listener
    }

    inner class RecyclerViewViewHolder(private val binding: RecyclerViewBottomSheetFilterLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(filter: Filter,position: Int) {
            binding.textFilter.text = filter.filter
            if(selectedPosition == position){
                binding.textFilter.setBackgroundColor(Color.WHITE)
                binding.blueIndicator.setBackgroundColor(Color.parseColor("#1fa9e5"))
            } else {
                binding.textFilter.setBackgroundColor(Color.parseColor("#EAEAEA"))
                binding.blueIndicator.setBackgroundColor(Color.WHITE)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewViewHolder {
        return RecyclerViewViewHolder(
            RecyclerViewBottomSheetFilterLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerViewViewHolder, position: Int) {
        holder.bind(items[position],position)
        getItemId(position).let {
            holder.itemView.setOnClickListener {
                selectedPosition = position
                notifyDataSetChanged()
                mListener.onItemClicked(items[position], position)

            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}