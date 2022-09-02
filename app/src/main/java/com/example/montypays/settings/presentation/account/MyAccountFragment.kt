package com.example.montypays.settings.presentation.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.montypays.R
import com.example.montypays.authentication.presentation.AuthenticationViewModel
import com.example.montypays.databinding.FragmentMyAccountBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyAccountFragment : Fragment() {

    private lateinit var binding: FragmentMyAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyAccountBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val viewModel by viewModels<AuthenticationViewModel>()


        getUser(viewModel)
        binding.arrowBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.btDelete.setOnClickListener {
            findNavController().navigate(R.id.action_myAccountFragment_to_deleteFragment)
        }
        binding.constraintLayoutDelete.setOnClickListener {
            findNavController().navigate(R.id.action_myAccountFragment_to_deleteFragment)
        }

    }

    private fun getUser(viewModel: AuthenticationViewModel) {
        viewModel.getUser(binding.tvFullName,binding.tvWorkEmail)
    }
}