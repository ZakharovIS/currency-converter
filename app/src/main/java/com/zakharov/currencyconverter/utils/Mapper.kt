package com.zakharov.currencyconverter.utils

import com.zakharov.currencyconverter.data.entities.ConverterApiResponse
import com.zakharov.currencyconverter.data.entities.ExchangeRateModel

fun ConverterApiResponse.toExchangeRateModel(): ExchangeRateModel {
    return ExchangeRateModel(
        exchangeRate = this.data.entries.first().value.exchangeRate,
        date = this.meta.last_updated_at
    )
}