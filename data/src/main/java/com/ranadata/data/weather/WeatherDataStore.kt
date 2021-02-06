package com.ranadata.data.weather

import com.rana.myweatherapp.domain.Failure
import com.rana.myweatherapp.domain.Result
import com.rana.myweatherapp.domain.Success
import com.rana.myweatherapp.domain.weather.Weather
import com.rana.myweatherapp.domain.weather.WeatherRepository
import com.ranadata.data.BuildConfig
import com.ranadata.data.database.WeatherDBDataStore
import com.ranadata.data.openweathermap.OpenWeatherMapApi
import com.ranadata.data.weather.mappers.toWeather
import javax.inject.Inject
import javax.inject.Singleton

const val API_KEY = BuildConfig.OPENWEATHERMAP_API_KEY

@Singleton
class WeatherDataStore @Inject constructor(
    private val api: OpenWeatherMapApi,
    private val dBDataStore: WeatherDBDataStore
) :
    WeatherRepository {
    override suspend fun fetchWeatherData(lat: String, long: String): Result<List<Weather>> {

        return try {
            val weatherList = api.getWeatherByLatLong(lat, long, API_KEY).toWeather()
            dBDataStore.storePlace(weatherList)
            Success(weatherList)
        } catch (exception: Exception) {
            Failure(exception)
        }
    }
}
