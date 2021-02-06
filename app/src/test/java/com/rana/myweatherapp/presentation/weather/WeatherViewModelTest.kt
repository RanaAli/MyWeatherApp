package com.rana.myweatherapp.presentation.weather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import com.nhaarman.mockitokotlin2.whenever
import com.rana.myweatherapp.domain.Success
import com.rana.myweatherapp.domain.settings.Celsius
import com.rana.myweatherapp.domain.settings.GetTemperatureUnitUseCase
import com.rana.myweatherapp.domain.weather.Weather
import com.rana.myweatherapp.domain.weather.WeatherType
import com.rana.myweatherapp.domain.weather.WeatherUseCase
import com.rana.myweatherapp.presentation.settings.TemperatureUnit
import com.rana.myweatherapp.presentation.weather.model.TemperatureData
import com.rana.myweatherapp.presentation.weather.model.WeatherData
import com.rana.myweatherapp.presentation.weather.model.WeatherMapper
import com.rana.myweatherapp.shared.CoroutinesTestRule
import com.rana.myweatherapp.shared.getLiveDataValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

class WeatherViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val weatherUseCase: WeatherUseCase = mock()
    private val getTemperatureUnitUseCase: GetTemperatureUnitUseCase = mock()
    private val weatherMapper: WeatherMapper = mock()

    private fun withViewModel(): WeatherViewModel {
        return WeatherViewModel(weatherUseCase, getTemperatureUnitUseCase, weatherMapper)
    }

    @Test
    fun showWeatherForCityShouldReturnWeatherViewState() =
        coroutinesTestRule.testDispatcher.runBlockingTest {

            // given
            val placeName = "tempPlace"
            val degrees = 27.0
            val viewModel = withViewModel()
            val tempData = TemperatureData(degrees, TemperatureUnit.CELSIUS)
            val weather = createWeather(degrees)
            val weatherData = WeatherData(
                "TempPlace",
                tempData,
                -1,
                weather.windDirection,
                weather.windSpeed,
                weather.pressure,
                weather.humidity
            )
            val weatherList: ArrayList<Weather> = arrayListOf()
            weatherList.add(weather)
            val result = Success(weatherList)

            // when
            weatherUseCase.stub {
                onBlocking {
                    invoke("0", "0")
                }.doReturn(result)
            }

            whenever(weatherMapper.map(weather, Celsius)).thenReturn(weatherData)
            whenever(getTemperatureUnitUseCase.invoke()).thenReturn(Success(Celsius))
            viewModel.showWeather("0", "0")

            // then
            val viewState = getLiveDataValue(viewModel.viewState)!!
            assertThat(viewState.progress.peek()).isFalse()
            assertThat(viewState.data.first()).isEqualTo(weatherData)
        }
}

fun createWeather(degrees: Double): Weather {
    val pressure = 100.0
    val humidity = 100.0
    val windSpeed = 100.0
    val windDirection = 123.0
    return Weather(
        type = WeatherType.CLEAR,
        temperature = degrees,
        maxTemperature = degrees,
        minTemperature = degrees,
        description = "",
        pressure = pressure,
        humidity = humidity,
        windSpeed = windSpeed,
        windDirection = windDirection
    )
}
