package com.example.montypays.home



import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.montypays.R
import com.example.montypays.databinding.FragmentHomeBinding
import com.example.montypays.dialogs.sheets.FilterBottomSheet
import com.example.montypays.dialogs.sheets.QrPayBottomSheet
import com.example.montypays.dialogs.sheets.ReportingBottomSheet
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


@ExperimentalMaterialApi
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var currency = arrayOf("USD", "EURO", "LBP")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        val view = binding.root
        binding.transactionSheet.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                TextAndArrow()
            }
        }
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getCurrentDate()
        endIconDropDown()
        enterAmountListener()

        binding.dismiss.visibility = INVISIBLE
        binding.tvDate.visibility = INVISIBLE
        binding.tvDateCollapsed.visibility = VISIBLE

        BottomSheetBehavior.from(binding.reportSheet.reportSheet).apply {
            this.peekHeight = maxHeight
            this.state = BottomSheetBehavior.STATE_COLLAPSED

        }
        BottomSheetBehavior.from(binding.transactionSheet.transactionSheet).apply {
            this.peekHeight = maxHeight
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        BottomSheetBehavior.from(binding.homeSheet).apply {
            this.peekHeight = maxHeight
            this.state = BottomSheetBehavior.STATE_COLLAPSED
            bindings()
        }.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                bottomSheet.requestLayout()
                bottomSheet.invalidate()
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    binding.tvDateCollapsed.visibility = INVISIBLE
                    binding.dismiss.visibility = VISIBLE
                    binding.extend.visibility = INVISIBLE
                    binding.tvDate.visibility = VISIBLE
                    binding.topSheet.setBackgroundResource(R.drawable.layout_bg_state_changed)
                    binding.dismiss.setOnClickListener {
                        BottomSheetBehavior.from(binding.homeSheet).apply {
                            this.state = BottomSheetBehavior.STATE_COLLAPSED
                        }
                    }
                } else if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    binding.tvDateCollapsed.visibility = VISIBLE
                    binding.tvDate.visibility = INVISIBLE
                    binding.dismiss.visibility = INVISIBLE
                    binding.extend.visibility = VISIBLE
                    binding.extend.setOnClickListener {
                        BottomSheetBehavior.from(binding.homeSheet).apply {

                            this.state = BottomSheetBehavior.STATE_EXPANDED
                        }
                    }
                    binding.topSheet.setBackgroundResource(R.drawable.layout_bg_1)
                }
            }


            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }
        })
    }


    private fun bindings() {
        with(binding) {
            shapeableImageView4.setBackgroundColor(Color.parseColor("#ffffff"))
            textView18.setTextColor(Color.parseColor("#ffffff"))

            settings.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
            }
            filter.setOnClickListener {
                navigateToFilterBottomSheet()
            }
            reportSheet.filterReports.setOnClickListener {
                navigateToFilterBottomSheet()
            }
            transactionSheet.filterTransactions.setOnClickListener {
                navigateToFilterBottomSheet()
            }
            shapeableImageView4.setOnClickListener {
                it.setBackgroundColor(Color.parseColor("#ffffff"))
                textView18.setTextColor(Color.parseColor("#ffffff"))
                shapeableImageView5.setBackgroundColor(Color.parseColor("#A0D6ED"))
                shapeableImageView6.setBackgroundColor(Color.parseColor("#A0D6ED"))
                shapeableImageView7.setBackgroundColor(Color.parseColor("#A0D6ED"))
                textView19.setTextColor(Color.parseColor("#A0D6ED"))
                textView20.setTextColor(Color.parseColor("#A0D6ED"))
                textView21.setTextColor(Color.parseColor("#A0D6ED"))

                homeSheet.visibility = VISIBLE
                reportSheet.root.visibility = INVISIBLE
                transactionSheet.root.visibility = INVISIBLE
                qrPaySheet.root.visibility = INVISIBLE
            }
            shapeableImageView5.setOnClickListener {
                shapeableImageView4.setBackgroundColor(Color.parseColor("#A0D6ED"))
                it.setBackgroundColor(Color.parseColor("#ffffff"))
                shapeableImageView6.setBackgroundColor(Color.parseColor("#A0D6ED"))
                shapeableImageView7.setBackgroundColor(Color.parseColor("#A0D6ED"))
                textView19.setTextColor(Color.parseColor("#ffffff"))
                textView18.setTextColor(Color.parseColor("#A0D6ED"))
                textView20.setTextColor(Color.parseColor("#A0D6ED"))
                textView21.setTextColor(Color.parseColor("#A0D6ED"))
                reportSheet.root.visibility = VISIBLE
                homeSheet.visibility = INVISIBLE
                transactionSheet.root.visibility = INVISIBLE
                qrPaySheet.root.visibility = INVISIBLE


            }
            shapeableImageView6.setOnClickListener {
                shapeableImageView4.setBackgroundColor(Color.parseColor("#A0D6ED"))
                shapeableImageView5.setBackgroundColor(Color.parseColor("#A0D6ED"))
                it.setBackgroundColor(Color.parseColor("#ffffff"))
                shapeableImageView7.setBackgroundColor(Color.parseColor("#A0D6ED"))
                textView20.setTextColor(Color.parseColor("#ffffff"))
                textView18.setTextColor(Color.parseColor("#A0D6ED"))
                textView19.setTextColor(Color.parseColor("#A0D6ED"))
                textView21.setTextColor(Color.parseColor("#A0D6ED"))

                transactionSheet.root.visibility = VISIBLE
                reportSheet.root.visibility = INVISIBLE
                homeSheet.visibility = INVISIBLE
                qrPaySheet.root.visibility = INVISIBLE
            }
            shapeableImageView7.setOnClickListener {
                shapeableImageView4.setBackgroundColor(Color.parseColor("#A0D6ED"))
                shapeableImageView5.setBackgroundColor(Color.parseColor("#A0D6ED"))
                shapeableImageView6.setBackgroundColor(Color.parseColor("#A0D6ED"))
                it.setBackgroundColor(Color.parseColor("#ffffff"))
                textView21.setTextColor(Color.parseColor("#ffffff"))
                textView18.setTextColor(Color.parseColor("#A0D6ED"))
                textView19.setTextColor(Color.parseColor("#A0D6ED"))
                textView20.setTextColor(Color.parseColor("#A0D6ED"))

                transactionSheet.root.visibility = INVISIBLE
                reportSheet.root.visibility = INVISIBLE
                homeSheet.visibility = INVISIBLE
                qrPaySheet.root.visibility = VISIBLE
            }
            qrPaySheet.filterQrPay.setOnClickListener {
                navigateToQrPaySheet()
            }
            reportSheet.tvReportingSheet.setOnClickListener {
                navigateToReportingSheet()
            }
            reportSheet.reportingType.setOnClickListener {
                navigateToReportingSheet()
            }
        }
    }

    private fun getCurrentDate() {
        val c: Date = Calendar.getInstance().time
        val df = SimpleDateFormat("MMM d", Locale.getDefault())
        val formattedDate: String = df.format(c)
        binding.tvDate.text = "Today $formattedDate"
        binding.tvDateCollapsed.text = "Today $formattedDate"
    }

    private fun navigateToFilterBottomSheet() {
        val sheet = FilterBottomSheet()
        sheet.show(requireActivity().supportFragmentManager, "FilterSheet")
    }

    private fun endIconDropDown() {
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(
                requireContext(),
                android.R.layout.simple_list_item_single_choice,
                currency
            )
        binding.qrPaySheet.edtCurrency.threshold = 1
        binding.qrPaySheet.edtCurrency.setAdapter(adapter)
    }

    private fun enterAmountListener() {
        binding.qrPaySheet.edtAmount.doOnTextChanged { text, _, _, _ ->
            if (text!!.isEmpty()) {
                binding.qrPaySheet.btGenerate.setBackgroundColor(Color.parseColor("#BCBCBC"))
                binding.qrPaySheet.btGenerate.isClickable = false
                binding.qrPaySheet.btGenerate.visibility = VISIBLE
                binding.qrPaySheet.lazyLoader.visibility = INVISIBLE
                binding.qrPaySheet.containerLazyLoader.visibility = INVISIBLE

            } else {
                binding.qrPaySheet.btGenerate.setBackgroundColor(Color.parseColor("#1fa9e5"))
                binding.qrPaySheet.btGenerate.isClickable = true
                onGenerateClicked()
            }
        }

    }

    private fun navigateToQrPaySheet() {
        val sheet = QrPayBottomSheet()
        sheet.show(requireActivity().supportFragmentManager, "QrPaySheet")
    }

    private fun navigateToReportingSheet() {
        val sheet = ReportingBottomSheet()
        sheet.show(requireActivity().supportFragmentManager, "ReportingSheet")
    }

    private fun onGenerateClicked() {
        binding.qrPaySheet.btGenerate.setOnClickListener {
            binding.qrPaySheet.containerLazyLoader.visibility = VISIBLE
            binding.qrPaySheet.lazyLoader.visibility = VISIBLE
            binding.qrPaySheet.btGenerate.visibility = INVISIBLE

            val currency = binding.qrPaySheet.edtCurrency.text.toString()
            val amountEntered = binding.qrPaySheet.edtAmount.text.toString().trim()

            val amount = "$amountEntered$currency"

            Handler(Looper.getMainLooper()).postDelayed({
                lifecycleScope.launch {
                    findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToGenerateQRCodeFragment(
                        amount = amount
                    ))
                }
            }, 3000)
        }
    }
}


@SuppressLint("SetTextI18n")
@ExperimentalMaterialApi
@Composable
private fun TextAndArrow(
) {
    Row() {
        AndroidView(factory = {
            TextView(it)
        }) { textView ->
            textView.apply {
                text = "Sales Transactions"
                textSize = 18f
                setTextColor(Color.BLACK)
            }.setOnClickListener {

            }
        }
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_keyboard_arrow_down_24),
            contentDescription = "TransactionSheet",
            modifier = Modifier.clickable {

            }
        )
    }
}


