package com.rana.myweatherapp.domain.settings

sealed class TemperatureUnit

object Celsius : TemperatureUnit()
object Fahrenheit : TemperatureUnit()
