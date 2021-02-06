package com.rana.myweatherapp.domain.weather

import com.rana.myweatherapp.domain.Result

interface WeatherRepository {
    suspend fun fetchWeatherData(lat: String, long: String): Result<List<Weather>>
}
