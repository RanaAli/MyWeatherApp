package com.ranadata.data.database

import android.content.Context
import com.rana.myweatherapp.domain.database.WeatherDBRepository
import com.rana.myweatherapp.domain.weather.Weather
import com.ranadata.data.database.entities.fromWeather
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherDBDataStore @Inject constructor(
    @ApplicationContext private val context: Context,
    private val weatherDbDao: WeatherDbDao
) : WeatherDBRepository {

    override suspend fun storePlace(data: List<Weather>) {
        data.map {
            val placeEntity = fromWeather(it)
            weatherDbDao.insert(placeEntity)
        }
    }
}
