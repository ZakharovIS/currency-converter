package com.zakharov.currencyconverter.data.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ConverterApiResponse(
    @Json(name = "success") val success: Boolean,
    @Json(name = "query") val query: Query,
    @Json(name = "info") val info: Info,
    @Json(name = "date") val date: String,
    @Json(name = "result") val result: Double
)

@JsonClass(generateAdapter = true)
data class Query(
    @Json(name = "from") val from: String,
    @Json(name = "to") val to: String,
    @Json(name = "amount") val amount: Double
)

@JsonClass(generateAdapter = true)
data class Info(
    @Json(name = "timestamp") val timestamp: Long,
    @Json(name = "rate") val rate: Double
)