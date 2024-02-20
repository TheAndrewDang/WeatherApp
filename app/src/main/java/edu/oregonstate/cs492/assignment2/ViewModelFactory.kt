package edu.oregonstate.cs492.assignment2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.oregonstate.cs492.assignment2.data.ForecastRepository
// Import your ViewModel(s) here
import edu.oregonstate.cs492.assignment2.WeatherViewModel

class ViewModelFactory(private val repository: ForecastRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WeatherViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
