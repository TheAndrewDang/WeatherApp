package edu.oregonstate.cs492.assignment2.data

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json

@JsonClass(generateAdapter = true)
data class ForecastResults(
    @Json(name = "list") val list: List<ForecastPeriod>
)