package com.example.montypays.dialogs.sheets


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import com.example.montypays.R
import com.example.montypays.adapters.FilterRecyclerViewAdapter
import com.example.montypays.databinding.FilterBottomSheetLayoutBinding
import com.example.montypays.databinding.RecyclerViewBottomSheetFilterLayoutBinding
import com.example.montypays.dataclass.Filter
import com.example.montypays.dialogs.MyDatePickerDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class FilterBottomSheet : BottomSheetDialogFragment() {
    private lateinit var binding: FilterBottomSheetLayoutBinding
    private lateinit var filterRecyclerViewAdapter: FilterRecyclerViewAdapter
    private lateinit var bindingText: RecyclerViewBottomSheetFilterLayoutBinding
    private val chkArrayForMerchant = arrayOfNulls<CheckBox>(3)
    private val chkArrayForStatus = arrayOfNulls<CheckBox>(3)
    private val chkArrayForCardType = arrayOfNulls<CheckBox>(2)
    private val chkArrayForCurrency = arrayOfNulls<CheckBox>(3)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FilterBottomSheetLayoutBinding.inflate(layoutInflater)
        bindingText = RecyclerViewBottomSheetFilterLayoutBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.closeSheet.setOnClickListener {
            this.dismiss()
        }
        binding.one.layoutFrom.setEndIconDrawable(R.drawable.ic_baseline_date_range_24)
        binding.one.layoutFrom.setEndIconOnClickListener {
            val picker = MyDatePickerDialog(requireActivity())
            picker.setListener(object : MyDatePickerDialog.OnOkClicked {
                override fun onClick(item: String?) {
                    binding.one.edtFrom.setText(item)
                }
            })
        }
        binding.one.layoutTo.setEndIconDrawable(R.drawable.ic_baseline_date_range_24)
        binding.one.layoutTo.setEndIconOnClickListener {
            val picker = MyDatePickerDialog(requireActivity())
            picker.setListener(object : MyDatePickerDialog.OnOkClicked {
                override fun onClick(item: String?) {
                    binding.one.edtTo.setText(item)
                }
            })
        }


        isChecked()


        val filterSizes = mutableListOf(
            Filter(
                "   Periodicals"
            ),
            Filter(
                "   Data Range"
            ),
            Filter(
                "   Merchant"
            ),
            Filter(
                "   Status"
            ),
            Filter(
                "   Card Type"
            ),
            Filter(
                "   Currency"
            )
        )

        filterRecyclerViewAdapter = FilterRecyclerViewAdapter(filterSizes)
        binding.recyclerViewFilter.adapter = filterRecyclerViewAdapter



        filterRecyclerViewAdapter.setOnItemClickListener(object :
            FilterRecyclerViewAdapter.OnItemClickListener {
            override fun onItemClicked(filter: Filter, position: Int) {

                when (filterRecyclerViewAdapter.items[position].filter) {
                    filterRecyclerViewAdapter.items[0].filter -> binding.zero.root.visibility =
                        View.VISIBLE.also {
                            indexOne()
                            indexTwo()
                            indexFour()
                            indexThree()
                            indexFive()
                        }
                    filterRecyclerViewAdapter.items[1].filter -> binding.one.root.visibility =
                        View.VISIBLE.also {
                            indexZero()
                            indexTwo()
                            indexThree()
                            indexFour()
                            indexFive()
                        }

                    filterRecyclerViewAdapter.items[2].filter -> binding.two.root.visibility =
                        View.VISIBLE.also {
                            indexZero()
                            indexOne()
                            indexFour()
                            indexThree()
                            indexFive()
                        }
                    filterRecyclerViewAdapter.items[3].filter -> binding.three.root.visibility =
                        View.VISIBLE.also {
                            indexZero()
                            indexOne()
                            indexFour()
                            indexTwo()
                            indexFive()
                        }
                    filterRecyclerViewAdapter.items[4].filter -> binding.four.root.visibility =
                        View.VISIBLE.also {
                            indexZero()
                            indexOne()
                            indexTwo()
                            indexFive()
                            indexThree()
                        }
                    filterRecyclerViewAdapter.items[5].filter -> binding.five.root.visibility =
                        View.VISIBLE.also {
                            indexZero()
                            indexOne()
                            indexTwo()
                            indexThree()
                            indexFour()
                        }
                    else -> Toast.makeText(requireContext(), "testing", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun isChecked() {

        // For Merchant
        chkArrayForMerchant[0] = binding.two.checkBox
        chkArrayForMerchant[0]!!.setOnClickListener(mListenerForMerchant)

        chkArrayForMerchant[1] = binding.two.checkBox2
        chkArrayForMerchant[1]!!.setOnClickListener(mListenerForMerchant)

        chkArrayForMerchant[2] = binding.two.checkBox3
        chkArrayForMerchant[2]!!.setOnClickListener(mListenerForMerchant)

        // For Statuses
        chkArrayForStatus[0] = binding.three.checkBox
        chkArrayForStatus[0]!!.setOnClickListener(mListenerForStatus)

        chkArrayForStatus[1] = binding.three.checkBox2
        chkArrayForStatus[1]!!.setOnClickListener(mListenerForStatus)

        chkArrayForStatus[2] = binding.three.checkBox3
        chkArrayForStatus[2]!!.setOnClickListener(mListenerForStatus)

        // For CardType
        chkArrayForCardType[0] = binding.four.checkBox
        chkArrayForCardType[0]!!.setOnClickListener(mListenerForCardType)

        chkArrayForCardType[1] = binding.four.checkBox2
        chkArrayForCardType[1]!!.setOnClickListener(mListenerForCardType)

        // For Currency
        chkArrayForCurrency[0] = binding.five.checkBox
        chkArrayForCurrency[0]!!.setOnClickListener(mListenerForCurrency)

        chkArrayForCurrency[1] = binding.five.checkBox2
        chkArrayForCurrency[1]!!.setOnClickListener(mListenerForCurrency)

        chkArrayForCurrency[2] = binding.five.checkBox3
        chkArrayForCurrency[2]!!.setOnClickListener(mListenerForCurrency)

    }

    private fun indexFive() {
        binding.five.root.visibility = View.GONE
    }

    private fun indexFour() {
        binding.four.root.visibility = View.GONE
    }

    private fun indexTwo() {
        binding.two.root.visibility = View.GONE
    }

    private fun indexZero() {
        binding.zero.root.visibility = View.GONE
    }

    private fun indexOne() {
        binding.one.root.visibility = View.GONE
    }

    private fun indexThree() {
        binding.three.root.visibility = View.GONE
    }


    private val mListenerForMerchant: View.OnClickListener = View.OnClickListener { v ->
        val checkedId = v.id
        for (i in chkArrayForMerchant.indices) {
            val current: CheckBox? = chkArrayForMerchant[i]
            current!!.isChecked = current.id == checkedId
        }
    }

    private val mListenerForStatus: View.OnClickListener = View.OnClickListener { v ->
        val checkedId = v.id
        for (i in chkArrayForStatus.indices) {
            val current: CheckBox? = chkArrayForStatus[i]
            current!!.isChecked = current.id == checkedId
        }
    }

    private val mListenerForCardType: View.OnClickListener = View.OnClickListener { v->
        val checkedId = v.id
        for(i in chkArrayForCardType.indices){
            val current: CheckBox? = chkArrayForCardType[i]
            current!!.isChecked = current.id == checkedId
        }
    }

    private val mListenerForCurrency: View.OnClickListener = View.OnClickListener { v->
        val checkedId = v.id
        for(i in chkArrayForCurrency.indices){
            val current: CheckBox? = chkArrayForCurrency[i]
            current!!.isChecked = current.id == checkedId
        }
    }
}