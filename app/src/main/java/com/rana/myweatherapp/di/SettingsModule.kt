package com.rana.myweatherapp.di

import com.ranadata.data.settings.SettingsDataStore
import com.rana.myweatherapp.domain.settings.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object SettingsModule {

    @Singleton
    @Provides
    fun provideSettingsModule(
        settingsRepository: SettingsDataStore
    ): SettingsRepository =
        settingsRepository
}
