package com.rana.myweatherapp.di

import android.content.Context
import com.ranadata.data.database.WeatherDbDao
import com.rana.myweatherapp.domain.database.WeatherDBRepository
import com.ranadata.data.database.WeatherDBDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object PlacesModule {

    @Singleton
    @Provides
    fun providePlacesRemoteDataStore(
        @ApplicationContext context: Context,
        weatherDbDao: WeatherDbDao
    ): WeatherDBRepository =
        WeatherDBDataStore(context, weatherDbDao)
}
