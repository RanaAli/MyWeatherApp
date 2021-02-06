package com.rana.myweatherapp.domain.settings

import com.rana.myweatherapp.domain.Result
import javax.inject.Inject

class GetSettingsUseCase @Inject constructor(private val settingsRepository: SettingsRepository) {

    operator fun invoke(): Result<Settings> {
        return settingsRepository.settings()
    }
}
