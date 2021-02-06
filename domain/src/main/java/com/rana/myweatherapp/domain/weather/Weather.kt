package com.rana.myweatherapp.domain.weather

data class Weather(
    val description: String = "",
    val type: WeatherType = WeatherType.UNDEFINED,
    val temperature: Double = 0.0,
    val maxTemperature: Double = 0.0,
    val minTemperature: Double = 0.0,
    val pressure: Double = 0.0,
    val humidity: Double = 0.0,
    val windSpeed: Double = 0.0,
    val windDirection: Double = 0.0
)

enum class WeatherType(val value: Int) {
    STORM(0),
    LIGHT_RAIN(1),
    RAIN(2),
    SNOW(3),
    FOG(4),
    CLEAR(5),
    LIGHT_CLOUDS(6),
    CLOUDS(7),
    UNDEFINED(8);

    companion object {
        fun fromInt(value: Int) = values().first { it.value == value }
    }
}
