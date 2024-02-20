package edu.oregonstate.cs492.assignment2.data

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastService {
    @GET("data/2.5/forecast")
    suspend fun searchWeather(
        @Query("q") query: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "imperial"
    ): ForecastResults

    companion object {
        private const val BASE_URL = "https://api.openweathermap.org/"

        fun create(): ForecastService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(ForecastService::class.java)
        }
    }
}
