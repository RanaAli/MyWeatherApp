package com.ranadata.data.openweathermap

import com.ranadata.data.weather.entity.CityWeatherForecastEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapApi {

    @GET("onecall")
    suspend fun getWeatherByLatLong(
        @Query("lat") lat: String,
        @Query("lon") long: String,
        @Query("appid") apiKey: String
    ): CityWeatherForecastEntity
}
