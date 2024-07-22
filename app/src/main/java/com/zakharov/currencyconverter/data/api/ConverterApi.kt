package com.zakharov.currencyconverter.data.api

import com.zakharov.currencyconverter.data.entities.ConverterApiResponse
import retrofit2.http.GET

interface ConverterApi {

    @GET()
    suspend fun convertCurrency() : ConverterApiResponse
}