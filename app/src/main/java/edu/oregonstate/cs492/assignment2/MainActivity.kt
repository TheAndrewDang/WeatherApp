package edu.oregonstate.cs492.assignment2

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.progressindicator.CircularProgressIndicator
import android.view.View
import android.widget.TextView
import edu.oregonstate.cs492.assignment2.data.ForecastRepository
import edu.oregonstate.cs492.assignment2.data.ForecastService

class MainActivity : AppCompatActivity() {
    private val viewModel: WeatherViewModel by viewModels {
        ViewModelFactory(ForecastRepository(ForecastService.create()))
    }

    private lateinit var forecastListRV: RecyclerView
    private lateinit var searchErrorTV: TextView
    private lateinit var loadingIndicator: CircularProgressIndicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchErrorTV = findViewById(R.id.tv_search_error)
        loadingIndicator = findViewById(R.id.loading_indicator)

        forecastListRV = findViewById(R.id.rv_forecast_list)
        forecastListRV.layoutManager = LinearLayoutManager(this)
        forecastListRV.setHasFixedSize(true)

        val forecastAdapter = ForecastAdapter(emptyList())
        forecastListRV.adapter = forecastAdapter

        viewModel.forecast.observe(this) { result ->
            result.fold(onSuccess = { forecast ->
                forecastAdapter.updateForecast(forecast)
                forecastListRV.visibility = View.VISIBLE
                loadingIndicator.visibility = View.GONE
                searchErrorTV.visibility = View.GONE
            }, onFailure = { error ->
                forecastListRV.visibility = View.GONE
                loadingIndicator.visibility = View.GONE
                searchErrorTV.visibility = View.VISIBLE
                searchErrorTV.text = getString(R.string.search_error, error.message)
            })
        }

        viewModel.loadingStatus.observe(this) { loadingStatus ->
            when (loadingStatus) {
                LoadingStatus.LOADING -> {
                    forecastListRV.visibility = View.INVISIBLE
                    loadingIndicator.visibility = View.VISIBLE
                    searchErrorTV.visibility = View.INVISIBLE
                }
                LoadingStatus.ERROR -> {
                    forecastListRV.visibility = View.INVISIBLE
                    loadingIndicator.visibility = View.INVISIBLE
                    searchErrorTV.visibility = View.VISIBLE
                }
                else -> {
                    forecastListRV.visibility = View.VISIBLE
                    loadingIndicator.visibility = View.INVISIBLE
                    searchErrorTV.visibility = View.INVISIBLE
                }
            }
        }

        viewModel.error.observe(this) { error ->
            if (error != null) {
                searchErrorTV.text = getString(R.string.search_error, error)
            }
        }

        viewModel.loadForecast("Corvallis,OR,US")
    }
}
