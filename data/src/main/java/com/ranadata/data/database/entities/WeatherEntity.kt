package com.ranadata.data.database.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.rana.myweatherapp.domain.weather.Weather
import com.rana.myweatherapp.domain.weather.WeatherType

@Entity(
    tableName = "weather", indices = [Index(
        value = [
            "description",
            "temperature",
            "maxTemperature",
            "minTemperature",
            "pressure",
            "humidity",
            "windSpeed",
            "windDirection"
        ],
        unique = true
    )]
)
data class WeatherEntity(
    val description: String = "",
    val type: Int = WeatherType.UNDEFINED.value,
    val temperature: Double = 0.0,
    val maxTemperature: Double = 0.0,
    val minTemperature: Double = 0.0,
    val pressure: Double = 0.0,
    val humidity: Double = 0.0,
    val windSpeed: Double = 0.0,
    val windDirection: Double = 0.0
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

fun fromWeather(weather: Weather) = WeatherEntity(
    weather.description,
    weather.type.value,
    weather.windDirection,
    weather.windSpeed,
    weather.pressure,
    weather.humidity
)

fun WeatherEntity.toWeather() = Weather(
    this.description,
    WeatherType.fromInt(this.type),
    this.windDirection,
    this.windSpeed,
    this.pressure,
    this.humidity
)
