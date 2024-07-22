package com.zakharov.currencyconverter.data

import com.zakharov.currencyconverter.data.api.ConverterApi
import com.zakharov.currencyconverter.data.entities.ExchangeRateModel
import com.zakharov.currencyconverter.utils.toExchangeRateModel
import javax.inject.Inject

class Repository @Inject constructor(
    private val converterApi: ConverterApi
) {

    suspend fun getExchangeRate(baseCurrency: String, currency: String): ExchangeRateModel {
        return converterApi.convertCurrency(
            baseCurrency = baseCurrency,
            currency = currency
        ).toExchangeRateModel()
    }
}