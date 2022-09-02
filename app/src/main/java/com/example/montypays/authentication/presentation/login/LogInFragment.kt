package com.example.montypays.authentication.presentation.login


import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView.BufferType
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.montypays.R
import com.example.montypays.authentication.data.AuthResult
import com.example.montypays.authentication.presentation.AuthenticationViewModel
import com.example.montypays.databinding.FragmentLogInBinding
import com.example.montypays.databinding.SavingPasswordDialogBinding
import com.example.montypays.dialogs.InternetConnectionDialog
import com.example.montypays.dialogs.SavePasswordDialog
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LogInFragment : Fragment() {
    private lateinit var binding: FragmentLogInBinding
    private lateinit var savePasswordBinding: SavingPasswordDialogBinding



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogInBinding.inflate(layoutInflater)
        savePasswordBinding = SavingPasswordDialogBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel by viewModels<AuthenticationViewModel>()

        viewModel.response.observe(viewLifecycleOwner) {
            when (it) {
                is AuthResult.Authorized -> {
                    SavePasswordDialog().show(
                        requireActivity().supportFragmentManager,
                        "savingPassword"
                    )
                }
                is AuthResult.Unauthorized -> {

                }
                is AuthResult.UnKnownError -> {
                    InternetConnectionDialog(
                        context = requireContext(),
                        requireActivity = requireActivity()
                    )
                }
            }
        }

        binding.edtUsername.doOnTextChanged { _, _, _, _ ->
            binding.linearLayout.setBackgroundResource(R.drawable.normal_background)
            binding.linearLayout2.setBackgroundResource(R.drawable.normal_background)
        }
        binding.edtPassword.doOnTextChanged { _, _, _, _ ->
            binding.linearLayout.setBackgroundResource(R.drawable.normal_background)
            binding.linearLayout2.setBackgroundResource(R.drawable.normal_background)
        }


        binding.btLogin.setOnClickListener {


            val workEmail = binding.edtUsername.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()
            val shake = AnimationUtils.loadAnimation(requireContext(), R.anim.shake)


            if (workEmail.isEmpty() && password.isEmpty()) {
                binding.linearLayout.startAnimation(shake)
                binding.linearLayout2.startAnimation(shake)
                binding.linearLayout.setBackgroundResource(R.drawable.error_background)
                binding.linearLayout2.setBackgroundResource(R.drawable.error_background)

            } else if (workEmail.isEmpty() && password.isNotEmpty()) {
                binding.linearLayout.setBackgroundResource(R.drawable.error_background)
                binding.linearLayout.startAnimation(shake)
            } else if (workEmail.isNotEmpty() && password.isEmpty()) {
                binding.linearLayout2.setBackgroundResource(R.drawable.error_background)
                binding.linearLayout2.startAnimation(shake)
            } else if (workEmail.isNotEmpty() && password.isNotEmpty()) {
                viewModel.login(
                    workEmail = workEmail,
                    password = password
                )
            }
        }




        binding.tvAccount.setOnClickListener {
            findNavController().navigate(R.id.action_logInFragment_to_signUpFragment)
        }
        binding.tvTermsCondition.setOnClickListener {
            findNavController().navigate(R.id.action_logInFragment_to_termsConditionsFragment2)
        }

        binding.tvForgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_logInFragment_to_forgotPasswordFragment)
        }
        underlineLoginTexts()
    }

    private fun underlineLoginTexts() {
        val content = SpannableString("Forgot Password?")
        val contentTerms = SpannableString("Terms and Conditions")
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        contentTerms.setSpan(UnderlineSpan(), 0, contentTerms.length, 0)
        binding.tvForgotPassword.text = content
        binding.tvTermsCondition.text = contentTerms

        val first = "Don't Have an account? "
        val next = "Sign Up Account"

        binding.tvAccount.setText(first + next, BufferType.SPANNABLE)
        val s = binding.tvAccount.text as Spannable
        val start = first.length
        val end = start + next.length
        s.setSpan(
            ForegroundColorSpan(Color.parseColor("#1fa9e5")),
            start,
            end,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        s.setSpan(UnderlineSpan(), start, end, 0)

    }
}