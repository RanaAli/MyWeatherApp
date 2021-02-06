package com.rana.myweatherapp.di

import android.content.Context
import androidx.room.Room
import com.ranadata.data.database.AppDatabase
import com.ranadata.data.database.WeatherDbDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "myweatherapp-db").build()
    }

    @Singleton
    @Provides
    fun provideWeatherDao(database: AppDatabase): WeatherDbDao {
        return database.weatherDao()
    }
}
