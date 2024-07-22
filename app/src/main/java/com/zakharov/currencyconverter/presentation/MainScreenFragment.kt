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
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.zakharov.currencyconverter.App
import com.zakharov.currencyconverter.R
import com.zakharov.currencyconverter.baseComponent
import com.zakharov.currencyconverter.data.entities.CurrencyCode
import com.zakharov.currencyconverter.databinding.FragmentMainScreenBinding
import com.zakharov.currencyconverter.di.DaggerBaseComponent
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
        var items = arrayOf<String>()
        CurrencyCode.entries.forEach {  it ->
            items += it.name
        }

        (binding.inputCurrencyEditText as? MaterialAutoCompleteTextView)?.setSimpleItems(items)
        binding.inputCurrencyEditText.setText(viewModel.getCurrencyConvertFrom(), false)
        binding.inputCurrencyEditText.setOnItemClickListener { _, _, _, itemIndex ->
            viewModel.setConvertFrom(itemIndex.toInt())
        }

        (binding.outputCurrencyEditText as? MaterialAutoCompleteTextView)?.setSimpleItems(items)
        binding.outputCurrencyEditText.setText(viewModel.getCurrencyConvertTo(), false)
        binding.outputCurrencyEditText.setOnItemClickListener { _, _, _, itemIndex ->
            viewModel.setConvertTo(itemIndex.toInt())
        }


        binding.btnConvert.setOnClickListener {

            viewModel.convert()

        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        binding.inputCurrencyEditText.setText(viewModel.getCurrencyConvertFrom(), false)
        binding.outputCurrencyEditText.setText(viewModel.getCurrencyConvertTo(), false)
        Log.d("conversionResult", viewModel.getCurrencyConvertFrom())
        Log.d("conversionResult", viewModel.getCurrencyConvertTo())

        super.onResume()
    }
}