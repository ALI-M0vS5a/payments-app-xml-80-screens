package com.example.montypays.authentication.presentation.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.montypays.R
import com.example.montypays.authentication.data.AuthResult
import com.example.montypays.authentication.presentation.AuthenticationViewModel
import com.example.montypays.databinding.FragmentOnBoardingBinding
import com.example.montypays.home.HomeActivity
import com.example.montypays.utils.startNewActivity
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardingBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel by viewModels<AuthenticationViewModel>()
        viewModel.response.observe(viewLifecycleOwner) {
            when (it) {
                is AuthResult.Authorized -> {
                    requireActivity()
                        .startNewActivity(HomeActivity::class.java)
                }
                is AuthResult.Unauthorized -> {

                }
                is AuthResult.UnKnownError -> {

                }
            }
        }

        binding.btSignup.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardingFragment_to_signUpFragment)
        }
        binding.btLogin.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardingFragment_to_logInFragment)

        }

        val images = listOf(
            R.drawable.bills,
            R.drawable.pay_by_link,
            R.drawable.online_and_mobile_checkout
        )
        val text = listOf(
            "Schedule all your recurring and ongoing \n" +
                    "payments all in one place through a\n" +
                    "reliable and advanced platform ",
            "Collect money globally and immediately\n" +
                    "Give your customers a convenient way to \n" +
                    "pay invoice online via payment links ",
            "We offer you the opportunity to process all \n" +
                    "online payments in a seamless way\n" +
                    "with one single platform"
        )
        val hardCodedText = listOf(
            "",
            "Pay by link or QR code",
            "Online & Mobile Checkout"
        )

        val adapter = OnBoardingViewPagerAdapter(images,hardCodedText,text)
        with(binding) {
            viewPager.adapter = adapter
            TabLayoutMediator(tabLayout,viewPager) { _, _ -> }.attach()
        }
    }
}