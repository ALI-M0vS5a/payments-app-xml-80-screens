package com.example.montypays.authentication.presentation.onboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.montypays.databinding.BoardingViewPagerLayoutBinding

class OnBoardingViewPagerAdapter(
    private val images: List<Int>,
    private val textBold: List<String>,
    private val text: List<String>
) : RecyclerView.Adapter<OnBoardingViewPagerAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(private val binding: BoardingViewPagerLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            with(binding) {
                myImage.setImageResource(images[position])
                imageTextHard.text = textBold[position]
                imageText.text = text[position]
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        return ViewPagerViewHolder(
            BoardingViewPagerLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return images.size and text.size and textBold.size
    }
}