package com.rana.myweatherapp.presentation.weather

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rana.myweatherapp.presentation.weather.model.WeatherData

class WeatherListAdapter : RecyclerView.Adapter<WeatherListItem>() {

    private var collection: ArrayList<WeatherData> = arrayListOf()

    var clickListener: (weatherData: WeatherData) -> Unit = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        WeatherListItem(parent)

    override fun onBindViewHolder(viewHolder: WeatherListItem, position: Int) =
        viewHolder.populate(collection[position], clickListener)

    override fun getItemCount() = collection.size

    fun setItems(items: ArrayList<WeatherData>) {
        collection.clear()
        collection.addAll(items)
        notifyDataSetChanged()
    }

}