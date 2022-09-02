package com.example.montypays.settings.presentation.terms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.montypays.R
import com.example.montypays.databinding.FragmentTermsConditionsBinding


class TermsConditionsFragment : Fragment() {

    private lateinit var binding: FragmentTermsConditionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTermsConditionsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.arrowBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.layoutSearch.setEndIconDrawable(R.drawable.ic_baseline_search_24)
    }
}