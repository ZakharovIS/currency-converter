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

    private val convertFrom = CurrencyCode.RUB
    private val convertTo = CurrencyCode.USD

    private val amount = 500

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

    fun getCurrencyConvertFrom() = convertFrom.name
    fun getCurrencyConvertTo() = convertTo.name

}