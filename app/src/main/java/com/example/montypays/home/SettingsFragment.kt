package com.example.montypays.home


import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.montypays.R
import com.example.montypays.authentication.presentation.AuthenticationViewModel
import com.example.montypays.databinding.DialogLogoutBinding
import com.example.montypays.databinding.FragmentSettingsBinding
import com.example.montypays.dialogs.LogoutDialog
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private lateinit var dialogBinding: DialogLogoutBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(layoutInflater)
        dialogBinding = DialogLogoutBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spannable()
        val viewModel by viewModels<AuthenticationViewModel>()


        binding.report.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_reportFragment)
        }

        binding.exitSettings.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.logout.setOnClickListener {
            LogoutDialog(
                context = requireContext(),
                requireActivity = requireActivity(),
                viewModel = viewModel,
                fragment = this
            )
        }
        binding.termsAndConditions.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_termsConditionsFragment)
        }
        binding.ibAccount.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_myAccountFragment)
        }
        binding.shareSettings.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_shareSettingsFragment)
        }
    }

    private fun spannable(){
        val text = "Log out"
        binding.logout.setText(text,TextView.BufferType.SPANNABLE)
        val s = binding.logout.text as Spannable
        s.setSpan(ForegroundColorSpan(Color.parseColor("#1fa9e5")),0,text.length,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    }
}