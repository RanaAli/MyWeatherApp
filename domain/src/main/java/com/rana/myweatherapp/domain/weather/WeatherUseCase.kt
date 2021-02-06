package com.rana.myweatherapp.domain.weather

import com.rana.myweatherapp.domain.Result
import javax.inject.Inject

class WeatherUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke(lat: String, long: String): Result<List<Weather>> {
        return weatherRepository.fetchWeatherData(lat, long)
    }
}
