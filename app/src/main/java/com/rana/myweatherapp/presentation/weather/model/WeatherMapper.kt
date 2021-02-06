package com.rana.myweatherapp.presentation.weather.model

import com.rana.myweatherapp.domain.settings.Celsius
import com.rana.myweatherapp.domain.settings.Fahrenheit
import com.rana.myweatherapp.domain.settings.TemperatureUnit
import com.rana.myweatherapp.domain.weather.Weather
import com.rana.myweatherapp.presentation.settings.TemperatureUnit.*
import javax.inject.Inject

class WeatherMapper @Inject constructor() {

    fun map(weather: Weather, unit: TemperatureUnit): WeatherData {

        val tempData = when (unit) {
            Celsius -> TemperatureData(
                convertToCelsius(weather.temperature),
                CELSIUS
            )
            Fahrenheit -> TemperatureData(
                convertToFahrenheit(weather.temperature),
                FAHRENHEIT
            )
        }

        val iconRes = weather.type.iconResource()

        return WeatherData(
            "TempPlace",
            tempData,
            iconRes,
            weather.windDirection,
            weather.windSpeed,
            weather.pressure,
            weather.humidity
        )
    }
}
