package com.zakharov.currencyconverter.presentation

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zakharov.currencyconverter.data.entities.ResultModel
import com.zakharov.currencyconverter.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        setResult()
        binding.topAppBar.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        return binding.root
    }

    private fun setResult() {
        val resultModel =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getParcelable("ResultKey", ResultModel::class.java)
            } else {
                arguments?.getParcelable<ResultModel>("ResultKey")
            }
        if (resultModel != null) {
            binding.currencyFrom.text = resultModel.convertFrom
            binding.currencyTo.text = resultModel.convertTo
            binding.result.text = resultModel.result.toString()
            binding.currencyAmount.text = resultModel.amount.toString()
            binding.currencyExchangeRate.text = resultModel.exchangeRate.toString()
            binding.timeStamp.text = resultModel.date
        }
    }

}

