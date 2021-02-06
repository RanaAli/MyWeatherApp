package com.rana.myweatherapp.domain.settings

import com.rana.myweatherapp.domain.Result

interface SettingsRepository {

    fun changeTemperatureUnit(unit: TemperatureUnit)

    fun settings(): Result<Settings>

    fun temperatureUnit(): Result<TemperatureUnit>
}
