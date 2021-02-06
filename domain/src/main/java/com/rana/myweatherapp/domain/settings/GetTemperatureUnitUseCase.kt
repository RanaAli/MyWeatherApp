package com.rana.myweatherapp.domain.settings

import com.rana.myweatherapp.domain.Result
import javax.inject.Inject

class GetTemperatureUnitUseCase @Inject constructor(private val settingsRepository: SettingsRepository) {

    operator fun invoke(): Result<TemperatureUnit> {
        return settingsRepository.temperatureUnit()
    }
}
