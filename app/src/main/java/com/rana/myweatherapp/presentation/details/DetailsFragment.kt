package com.rana.myweatherapp.presentation.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rana.myweatherapp.databinding.FragmentDetailsBinding
import com.rana.myweatherapp.presentation.weather.model.getDegreesRepresentation
import com.rana.myweatherapp.presentation.weather.model.WeatherData

const val DATA_KEY = "DATA_KEY"

class DetailsFragment : Fragment() {

    lateinit var binding: FragmentDetailsBinding

    private var data: WeatherData? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getParcelable<WeatherData>(DATA_KEY)?.let {
            data = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        data?.let {
            with(binding) {
                tvCityName.text = it.place
                context?.let { ctx ->
                    tvDegrees.text = getDegreesRepresentation(ctx, it.tempData)
                }
                ivWeather.setImageResource(it.iconRes)
                tvWindDirection.text = it.windDirection.toString()
                tvWindSpeed.text = it.windSpeed.toString()
                tvPressure.text = it.pressure.toString()
                tvHumidity.text = it.humidity.toString()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstanceBundle(data: WeatherData) = Bundle().apply {
            putParcelable(DATA_KEY, data)
        }
    }

}