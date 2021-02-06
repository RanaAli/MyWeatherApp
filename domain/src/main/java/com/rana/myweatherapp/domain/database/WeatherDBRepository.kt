package com.rana.myweatherapp.domain.database

import com.rana.myweatherapp.domain.weather.Weather

interface WeatherDBRepository {
    suspend fun storePlace(place: List<Weather>)
}
