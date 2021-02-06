 package com.rana.myweatherapp.presentation.weather.model

import android.os.Parcelable
import com.rana.myweatherapp.presentation.settings.TemperatureUnit
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherData(
    val place: String,
    val tempData: TemperatureData,
    val iconRes: Int,
    val windDirection: Double,
    val windSpeed: Double,
    val pressure: Double,
    val humidity: Double
): Parcelable

@Parcelize
data class TemperatureData(val degrees: Double, val tempUnit: TemperatureUnit): Parcelable
