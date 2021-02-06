package com.rana.myweatherapp.presentation.weather.model

import com.rana.myweatherapp.core.Event
import com.rana.myweatherapp.presentation.weather.model.WeatherData

data class WeatherViewState(
    val progress: Event<Boolean>,
    val error: Exception?,
    val data: ArrayList<WeatherData>
)
