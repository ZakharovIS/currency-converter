package com.zakharov.currencyconverter.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zakharov.currencyconverter.data.Repository
import com.zakharov.currencyconverter.data.entities.CurrencyCode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val repository: Repository
) : ViewModel() {

    private var convertFrom = CurrencyCode.RUB
    private var convertTo = CurrencyCode.USD

    private var amount: Double = 0.0

    private val _convertionResult = MutableStateFlow<Double?>(null)
    val convertionResult = _convertionResult.asStateFlow()


    fun convert() {
        viewModelScope.launch {
            _convertionResult.value = repository.getExchangeRate(
                baseCurrency = convertFrom.name,
                currency = convertTo.name
            ).exchangeRate * amount
            Log.d("conversionResult", "${convertionResult.value}")
        }
    }

    fun setConvertFrom(index: Int) {
        convertFrom = CurrencyCode.entries[index]
        Log.d("conversionResult", "$convertFrom")
    }

    fun setConvertTo(index: Int) {
        convertTo = CurrencyCode.entries[index]
        Log.d("conversionResult", "$convertTo")
    }

    fun setAmount(amount: Double) {
        this.amount = amount
    }

    fun getCurrencyConvertFrom() = convertFrom.name
    fun getCurrencyConvertTo() = convertTo.name

}