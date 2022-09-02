package com.example.montypays.authentication.presentation.signup

import `in`.mayanknagwanshi.countrypicker.CountrySelectActivity
import `in`.mayanknagwanshi.countrypicker.bean.CountryData
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.view.*
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.montypays.ConnectivityObserver
import com.example.montypays.R
import com.example.montypays.authentication.data.AuthResult
import com.example.montypays.authentication.presentation.AuthenticationViewModel
import com.example.montypays.databinding.BottomSheetCompanySizeLayoutBinding
import com.example.montypays.databinding.FragmentSignUpBinding
import com.example.montypays.dialogs.sheets.CompanySizeBottomSheet
import com.example.montypays.dialogs.InternetConnectionDialog
import com.example.montypays.dialogs.LoadingDialog
import com.example.montypays.home.HomeActivity
import com.example.montypays.utils.startNewActivity
import com.example.montypays.utils.toast
import com.juanpabloprado.countrypicker.CountryPicker
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var bindingBottomSheet: BottomSheetCompanySizeLayoutBinding
    private lateinit var connectivityObserver: ConnectivityObserver


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        bindingBottomSheet = BottomSheetCompanySizeLayoutBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spannable()

        val viewModel by viewModels<AuthenticationViewModel>()
        val myDialog = LoadingDialog(
            requireActivity = requireActivity()
        )
        viewModel.response.observe(viewLifecycleOwner) {
            when (it) {
                is AuthResult.Authorized -> {
                    myDialog.hide()
                    requireActivity()
                        .startNewActivity(HomeActivity::class.java)
                    toast("You're authenticated!")
                }
                is AuthResult.Unauthorized -> {
                    myDialog.hide()
                }
                is AuthResult.UnKnownError -> {
                    myDialog.hide()
                    InternetConnectionDialog(
                        context = requireContext(),
                        requireActivity = requireActivity()
                    )
                }
            }
        }
        bindings()
        binding.btSignup.setOnClickListener {
            myDialog.show()

            with(binding) {
                val fullName = edtFullName.text.toString().trim()
                val workEmail = edtWorkEmail.text.toString().trim()
                val country = tvCountry2.text.toString().trim()
                val mobileNumber = edtCountryCode.text.toString()
                    .trim() + edtMobileNumber.text.toString().trim()
                val companyName = edtCompanyName.text.toString().trim()
                val website = edtWebsite.text.toString().trim()
                val password = edtPassword.text.toString().trim()
                val companySize = edtCompanySize.text.toString().trim()

                if (fullName.isNotEmpty() && workEmail.isNotEmpty() && country.isNotEmpty() && mobileNumber.isNotEmpty()
                    && companyName.isNotEmpty() && website.isNotEmpty() && password.isNotEmpty() && companySize.isNotEmpty()
                ) {
                    viewModel.signUp(
                        fullName = fullName,
                        workEmail = workEmail,
                        country = country,
                        mobileNumber = mobileNumber,
                        companyName = companyName,
                        website = website,
                        password = password,
                        companySize = companySize
                    )
                } else {
                    InternetConnectionDialog(
                        context = requireContext(),
                        requireActivity = requireActivity()
                    ).create()
                    myDialog.hide()

                }
            }
        }
    }

    private fun bindings() {

        binding.linearLayoutCountry.setOnClickListener {
            picker()
        }
        binding.linearLayoutCountry.setEndIconDrawable(R.drawable.ic_baseline_keyboard_arrow_down_24)
        binding.linearLayoutCountry.setEndIconOnClickListener {
            picker()
        }
        binding.tvCountry2.setOnClickListener {
            picker()
        }

        binding.edtCountryCode.setOnClickListener {
            intentToPicker()
        }
        binding.ibCountryCodePicker.setOnClickListener {
            intentToPicker()
        }
        binding.linearLayoutCompanySize.setEndIconDrawable(R.drawable.ic_baseline_keyboard_arrow_down_24)
        binding.linearLayoutCompanySize.setEndIconOnClickListener {
            navigateToBottomSheet()
        }

        binding.linearLayoutCompanySize.setOnClickListener {
            navigateToBottomSheet()
        }
        binding.edtCompanySize.setOnClickListener {
            navigateToBottomSheet()
        }

        binding.tvAccount.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_logInFragment)
        }
        binding.tvAccount.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_logInFragment)
        }

    }

    @SuppressLint("ResourceType")
    private fun navigateToBottomSheet() {
        val sheet = CompanySizeBottomSheet()
        sheet.setListener(object : CompanySizeBottomSheet.OnApplyButtonClicked {
            override fun onClick(item: String?) {
                binding.edtCompanySize.setText(item)
            }
        })
        sheet.show(requireActivity().supportFragmentManager, "BottomSheetDialog")
    }

    private fun intentToPicker() {
        val intent = Intent(requireContext(), CountrySelectActivity::class.java)
        intent.putExtra(
            CountrySelectActivity.EXTRA_SELECTED_COUNTRY,
            CountryData("IN")
        )
        getCountryCodeFromDialog.launch(intent)
    }

    private fun picker() {
        val picker: CountryPicker = CountryPicker.getInstance(
            "Select Country"
        ) { name, _ ->
            binding.tvCountry2.setText(name)
            val dialogFragment: DialogFragment =
                requireActivity().supportFragmentManager.findFragmentByTag("CountryPicker") as DialogFragment
            dialogFragment.dismiss()
        }
        picker.show(requireActivity().supportFragmentManager, "CountryPicker")
    }

    private val getCountryCodeFromDialog = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            val countryData =
                it.data!!.getSerializableExtra(CountrySelectActivity.RESULT_COUNTRY_DATA) as CountryData?
            binding.edtCountryCode.text = countryData!!.countryISD
        }
    }
    private fun spannable(){
        val first = "Already have an account? "
        val next = "Log in"
        binding.tvAccount.setText(first + next, TextView.BufferType.SPANNABLE)
        val s = binding.tvAccount.text as Spannable
        val start = first.length
        val end = start + next.length
        s.setSpan(ForegroundColorSpan(Color.parseColor("#1fa9e5")),start,end,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        s.setSpan(UnderlineSpan(),start,end,0)

    }

}



