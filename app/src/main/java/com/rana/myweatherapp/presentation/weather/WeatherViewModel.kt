package com.rana.myweatherapp.presentation.weather

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rana.myweatherapp.core.Event
import com.rana.myweatherapp.domain.Failure
import com.rana.myweatherapp.domain.Success
import com.rana.myweatherapp.domain.settings.Celsius
import com.rana.myweatherapp.domain.settings.GetTemperatureUnitUseCase
import com.rana.myweatherapp.domain.settings.TemperatureUnit
import com.rana.myweatherapp.domain.weather.Weather
import com.rana.myweatherapp.domain.weather.WeatherUseCase
import com.rana.myweatherapp.presentation.weather.model.WeatherData
import com.rana.myweatherapp.presentation.weather.model.WeatherMapper
import com.rana.myweatherapp.presentation.weather.model.WeatherViewState
import kotlinx.coroutines.launch

class WeatherViewModel @ViewModelInject constructor(
    val weatherUseCase: WeatherUseCase,
    val getTemperatureUnitUseCase: GetTemperatureUnitUseCase,
    private val weatherMapper: WeatherMapper
) : ViewModel() {

    private val _viewState = MutableLiveData<WeatherViewState>()
    val viewState: LiveData<WeatherViewState>
        get() = _viewState

    fun showWeather(lat: String, long: String) = viewModelScope.launch {
        showLoading()
        when (val weatherResult = weatherUseCase(lat, long)) {

            is Success -> {
                val tempUnit = getTemperatureUnit()

                val weatherDataList: ArrayList<WeatherData> = arrayListOf()

                for (weather: Weather in weatherResult.data)
                    weatherDataList.add(weatherMapper.map(weather, tempUnit))

                emitUiState(showSuccess = weatherDataList)
            }

            is Failure -> {
                emitUiState(showError = weatherResult.exception)
            }
        }
    }

    private fun getTemperatureUnit(): TemperatureUnit {
        return when (val result = getTemperatureUnitUseCase()) {
            is Success -> result.data
            is Failure -> Celsius
        }
    }

    private fun showLoading() {
        emitUiState(showProgress = Event(true))
    }

    private fun emitUiState(
        showProgress: Event<Boolean> = Event(false),
        showError: Exception? = null,
        showSuccess: ArrayList<WeatherData> = arrayListOf()
    ) {
        val viewState = WeatherViewState(
            showProgress,
            showError,
            showSuccess
        )
        _viewState.value = viewState
    }
}
