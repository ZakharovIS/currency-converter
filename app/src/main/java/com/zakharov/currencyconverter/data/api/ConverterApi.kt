package com.zakharov.currencyconverter.data.api

import com.zakharov.currencyconverter.BuildConfig
import com.zakharov.currencyconverter.data.entities.ConverterApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ConverterApi {

    @GET("latest")
    suspend fun convertCurrency(
        @Query("apikey") apiKey: String = BuildConfig.API_KEY,
        @Query("base_currency") baseCurrency: String,
        @Query("currencies") currency: String
    ): ConverterApiResponse
}