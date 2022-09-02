package com.example.montypays.authentication.presentation.forgot

import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.example.montypays.R
import com.example.montypays.databinding.FragmentForgotPasswordBinding


class ForgotPasswordFragment : Fragment() {
    private lateinit var binding: FragmentForgotPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForgotPasswordBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        underLineTexts()

        binding.ibBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.tvTermsCondition.setOnClickListener {
            findNavController().navigate(R.id.action_forgotPasswordFragment_to_termsConditionsFragment2)
        }
        binding.btReset.setOnClickListener {
            val shake = AnimationUtils.loadAnimation(requireContext(), R.anim.shake)
            if(binding.etEmail.text?.isEmpty() == true){
                binding.layoutEmail.startAnimation(shake)
                binding.layoutEmail.setBackgroundResource(R.drawable.error_background)
            } else {
                findNavController().navigate(R.id.action_forgotPasswordFragment_to_resetPasswordScreen)
            }
        }
    }

    private fun underLineTexts(){
        val contentTerms = SpannableString("Terms and Conditions")
        contentTerms.setSpan(UnderlineSpan(), 0, contentTerms.length, 0)
        binding.tvTermsCondition.text = contentTerms

    }
}