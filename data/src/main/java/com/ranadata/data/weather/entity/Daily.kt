package com.ranadata.data.weather.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Daily {
    @SerializedName("dt")
    @Expose
    var dt: Double? = null

    @SerializedName("sunrise")
    @Expose
    var sunrise: Double? = null

    @SerializedName("sunset")
    @Expose
    var sunset: Double? = null

    @SerializedName("temp")
    @Expose
    var temp: Temp? = null

    @SerializedName("feels_like")
    @Expose
    var feelsLike: FeelsLike? = null

    @SerializedName("pressure")
    @Expose
    var pressure: Double? = null

    @SerializedName("humidity")
    @Expose
    var humidity: Double? = null

    @SerializedName("dew_point")
    @Expose
    var dewPoint: Double? = null

    @SerializedName("wind_speed")
    @Expose
    var windSpeed: Double? = null

    @SerializedName("wind_deg")
    @Expose
    var windDeg: Double? = null

    @SerializedName("weather")
    @Expose
    var weather: List<Weather>? = null

    @SerializedName("clouds")
    @Expose
    var clouds: Double? = null

    @SerializedName("pop")
    @Expose
    var pop: Double? = null

    @SerializedName("uvi")
    @Expose
    var uvi: Double? = null
}