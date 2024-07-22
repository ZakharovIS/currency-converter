package com.zakharov.currencyconverter.data.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ConverterApiResponse(
    @Json(name = "meta") val meta: Meta,
    @Json(name = "data") val data: Map<String, Currency>
)

@JsonClass(generateAdapter = true)
data class Meta(
    @Json(name = "last_updated_at") val last_updated_at: String
)

@JsonClass(generateAdapter = true)
data class Currency(
    @Json(name = "code") val currencyCode: String,
    @Json(name = "value") val exchangeRate: Double
)