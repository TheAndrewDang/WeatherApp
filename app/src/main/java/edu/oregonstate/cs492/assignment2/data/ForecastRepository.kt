package edu.oregonstate.cs492.assignment2.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ForecastRepository(private val service: ForecastService) {

    private var cachedForecast: List<ForecastPeriod>? = null

    suspend fun loadForecast(query: String): Result<List<ForecastPeriod>> = withContext(Dispatchers.IO) {
        if (cachedForecast == null) {
            try {
                val response = service.searchWeather(query, "50a55194483f2d519029f630d28bcec0", "imperial")
                cachedForecast = response.list
                Result.success(response.list)
            } catch (e: Exception) {
                Result.failure(e)
            }
        } else {
            Result.success(cachedForecast!!)
        }
    }
}
