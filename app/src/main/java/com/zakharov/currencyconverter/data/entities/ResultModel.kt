package com.zakharov.currencyconverter.data.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResultModel(
    val convertFrom: String,
    val convertTo: String,
    val amount: Double,
    val exchangeRate: Double,
    val result: Double,
    val date: String
): Parcelable
