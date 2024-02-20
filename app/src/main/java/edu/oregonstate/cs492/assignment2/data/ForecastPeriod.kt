package edu.oregonstate.cs492.assignment2.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastPeriod(
    @Json(name = "dt_txt") val dateTime: String,
    val main: Main,
    val weather: List<Weather>,
    @Json(name = "pop") val probabilityOfPrecipitation: Double
)

@JsonClass(generateAdapter = true)
data class Main(
    @Json(name = "temp_min") val tempMin: Double,
    @Json(name = "temp_max") val tempMax: Double
)

@JsonClass(generateAdapter = true)
data class Weather(
    val description: String
)