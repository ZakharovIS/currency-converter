package com.zakharov.currencyconverter.presentation

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.zakharov.currencyconverter.App
import com.zakharov.currencyconverter.R
import com.zakharov.currencyconverter.baseComponent
import com.zakharov.currencyconverter.data.entities.CurrencyCode
import com.zakharov.currencyconverter.databinding.FragmentMainScreenBinding
import com.zakharov.currencyconverter.di.DaggerBaseComponent
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

class MainScreenFragment : Fragment() {

    @Inject
    lateinit var vmFactory: MainScreenViewModelFactory

    private lateinit var viewModel: MainScreenViewModel

    private lateinit var binding: FragmentMainScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainScreenBinding.inflate(inflater)
        this.baseComponent().inject(this)
        viewModel = ViewModelProvider.create(this, vmFactory)[MainScreenViewModel::class]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setInputCurrency()
        setOutputCurrency()
        setCurrencyAmount()
        setButtonClickListener()
        setResultListener()
        setFailListener()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setFailListener() {
        lifecycleScope.launch {
            viewModel.showToast.collect {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setResultListener() {
        lifecycleScope.launch {
            viewModel.convertResult.collect { resultModel ->
                var bundle = Bundle()
                bundle.putParcelable("ResultKey", resultModel)
                val fragmentResult = ResultFragment()
                fragmentResult.arguments = bundle
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.fragment_container, fragmentResult
                    )
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    private fun setButtonClickListener() {
        binding.btnConvert.setOnClickListener {
            viewModel.setAmount(
                binding.inputCurrencyAmountEditText.text.toString().toDouble()
            )
            viewModel.convert()
        }
    }

    private fun setCurrencyAmount() {
        binding.inputCurrencyAmountEditText.setText(
            String.format(
                Locale.getDefault(),
                "%f",
                viewModel.getAmount()
            ), TextView.BufferType.EDITABLE
        )
    }

    private fun setOutputCurrency() {
        (binding.outputCurrencyEditText as? MaterialAutoCompleteTextView)?.setSimpleItems(viewModel.items)
        binding.outputCurrencyEditText.setText(viewModel.getCurrencyConvertTo(), false)
        binding.outputCurrencyEditText.setOnItemClickListener { _, _, _, itemIndex ->
            viewModel.setConvertTo(itemIndex.toInt())
        }
    }

    private fun setInputCurrency() {
        (binding.inputCurrencyEditText as? MaterialAutoCompleteTextView)?.setSimpleItems(viewModel.items)
        binding.inputCurrencyEditText.setText(viewModel.getCurrencyConvertFrom(), false)
        binding.inputCurrencyEditText.setOnItemClickListener { _, _, _, itemIndex ->
            viewModel.setConvertFrom(itemIndex.toInt())
        }
    }

    override fun onResume() {
        setInputCurrency()
        setOutputCurrency()
        super.onResume()
    }
}