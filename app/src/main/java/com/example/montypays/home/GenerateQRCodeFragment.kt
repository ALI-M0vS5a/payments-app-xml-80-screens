package com.example.montypays.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.montypays.databinding.GenerateQrcodeLayoutBinding


class GenerateQRCodeFragment : Fragment() {

    private lateinit var binding: GenerateQrcodeLayoutBinding
    private val args: GenerateQRCodeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = GenerateQrcodeLayoutBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val encoder = QRGEncoder(args.amount,null, QRGContents.Type.TEXT,800)
        binding.ivCode.setImageBitmap(encoder.bitmap)

        binding.close.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.amountEntered.text = args.amount
    }
}