package com.rana.myweatherapp.di

import com.rana.myweatherapp.BuildConfig
import com.rana.myweatherapp.domain.weather.WeatherRepository
import com.ranadata.data.database.WeatherDBDataStore
import com.ranadata.data.openweathermap.OpenWeatherMapApi
import com.ranadata.data.weather.WeatherDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val OPENWEATHERMAP_URL = BuildConfig.OPENWEATHERMAP_URL

@InstallIn(ApplicationComponent::class)
@Module
object OpenWeatherMapModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(OPENWEATHERMAP_URL)
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideOpenWeatherMapApi(retrofit: Retrofit): OpenWeatherMapApi {
        return retrofit.create(OpenWeatherMapApi::class.java)
    }

    @Provides
    fun provideOpenWeatherMapDataStore(
        openWeatherMapApi: OpenWeatherMapApi,
        dbDataStore: WeatherDBDataStore
    ): WeatherRepository {
        return WeatherDataStore(openWeatherMapApi, dbDataStore)
    }
}
