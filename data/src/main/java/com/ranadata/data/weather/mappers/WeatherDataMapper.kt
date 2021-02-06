package com.ranadata.data.weather.mappers

import com.ranadata.data.weather.entity.CityWeatherForecastEntity
import com.rana.myweatherapp.domain.weather.*

fun CityWeatherForecastEntity.toWeather(): List<Weather> {
    val result: MutableList<Weather> = arrayListOf()

    this.daily?.let { dailyList ->
        for (day in dailyList) {
            result.add(

                Weather(
                    humidity = day.humidity ?: 0.0,
                    pressure = day.pressure ?: 0.0,
                    temperature = day.temp?.day ?: 0.0,
                    minTemperature = day.temp?.min ?: 0.0,
                    maxTemperature = day.temp?.max ?: 0.0,
                    windDirection = day.windDeg ?: 0.0,
                    windSpeed = day.windSpeed ?: 0.0,
                    type = mapToWeatherType(day.weather?.first()?.id ?: 0)
                )
            )
        }
    }
    return result
}

fun mapToWeatherType(weatherId: Long): WeatherType {
    // Based on weather code data found at:
    // http://bugs.openweathermap.org/projects/api/wiki/Weather_Condition_Codes
    return when (weatherId) {
        in 200..232 -> WeatherType.STORM
        in 300..321 -> WeatherType.LIGHT_RAIN
        in 500..504 -> WeatherType.RAIN
        511L -> WeatherType.SNOW
        in 520..531 -> WeatherType.RAIN
        in 600..622 -> WeatherType.SNOW
        in 701..761 -> WeatherType.FOG
        761L, 781L -> WeatherType.STORM
        800L -> WeatherType.CLEAR
        801L -> WeatherType.LIGHT_CLOUDS
        in 802..804 -> WeatherType.CLOUDS
        else -> WeatherType.UNDEFINED
    }
}
