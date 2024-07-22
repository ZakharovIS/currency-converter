package com.zakharov.currencyconverter.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zakharov.currencyconverter.data.Repository
import com.zakharov.currencyconverter.data.entities.CurrencyCode
import com.zakharov.currencyconverter.data.entities.ExchangeRateModel
import com.zakharov.currencyconverter.data.entities.ResultModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val repository: Repository
) : ViewModel() {

    private var convertFrom = CurrencyCode.RUB
    private var convertTo = CurrencyCode.USD
    private var amount: Double = 0.0
    var items = arrayOf<String>()

    private val _convertResult = MutableStateFlow<ResultModel?>(null)
    val convertResult = _convertResult.asStateFlow().filterNotNull()

    private val _showToast = Channel<String>()
    val showToast = _showToast.receiveAsFlow()
        .mapNotNull { singleEvent ->
            singleEvent
        }

    init {
        CurrencyCode.entries.forEach { it ->
            items += it.name
        }
    }

    fun convert() {
        viewModelScope.launch {
            runCatching {
                repository.getExchangeRate(
                    baseCurrency = convertFrom.name,
                    currency = convertTo.name
                )
            }.onSuccess { result ->
                _convertResult.value = ResultModel(
                    convertFrom = convertFrom.name,
                    convertTo = convertTo.name,
                    amount = amount,
                    exchangeRate = result.exchangeRate,
                    result = amount * result.exchangeRate,
                    date = result.date
                )
                _convertResult.value = null
            }.onFailure {
                _showToast.send("Something went wrong")
            }
        }
    }

    fun setConvertFrom(index: Int) {
        convertFrom = CurrencyCode.entries[index]
    }

    fun setConvertTo(index: Int) {
        convertTo = CurrencyCode.entries[index]
    }

    fun setAmount(amount: Double) {
        this.amount = amount
    }

    fun getCurrencyConvertFrom() = convertFrom.name
    fun getCurrencyConvertTo() = convertTo.name
    fun getAmount() = amount
}