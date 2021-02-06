package com.rana.myweatherapp.presentation.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rana.myweatherapp.databinding.ViewHolderWeatherBinding
import com.rana.myweatherapp.presentation.weather.model.WeatherData
import com.rana.myweatherapp.presentation.weather.model.getDegreesRepresentation

class WeatherListItem(
    parent: ViewGroup,
    var binding: ViewHolderWeatherBinding = ViewHolderWeatherBinding.inflate(
        LayoutInflater.from(
            parent.context
        ), parent, false
    )
) : RecyclerView.ViewHolder(binding.root) {

    fun populate(data: WeatherData, clickListener: (weatherData: WeatherData) -> Unit) {
        binding.ivWeather.setImageResource(data.iconRes)
        binding.tvCityName.text = data.place
        binding.tvDegrees.text = getDegreesRepresentation(itemView.context, data.tempData)

        itemView.setOnClickListener { clickListener.invoke(data) }
    }
}
