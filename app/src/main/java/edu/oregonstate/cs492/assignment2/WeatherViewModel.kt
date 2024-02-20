package edu.oregonstate.cs492.assignment2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.oregonstate.cs492.assignment2.data.ForecastPeriod
import edu.oregonstate.cs492.assignment2.data.ForecastRepository
import kotlinx.coroutines.launch

class WeatherViewModel(private val repository: ForecastRepository) : ViewModel() {

    private val _forecast = MutableLiveData<Result<List<ForecastPeriod>>>(Result.success(emptyList()))
    val forecast: LiveData<Result<List<ForecastPeriod>>> = _forecast

    private val _loadingStatus = MutableLiveData<LoadingStatus>(LoadingStatus.SUCCESS)
    val loadingStatus: LiveData<LoadingStatus> = _loadingStatus

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error

    fun loadForecast(query: String) {
        _loadingStatus.value = LoadingStatus.LOADING
        viewModelScope.launch {
            val result = repository.loadForecast(query)
            result.onSuccess {
                _forecast.value = Result.success(it)
                _loadingStatus.value = LoadingStatus.SUCCESS
            }.onFailure {
                _error.value = it.message
                _loadingStatus.value = LoadingStatus.ERROR
            }
        }
    }
}
