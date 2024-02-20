package edu.oregonstate.cs492.assignment2

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.oregonstate.cs492.assignment2.data.ForecastPeriod
import java.text.SimpleDateFormat
import java.util.*

class ForecastAdapter(private var forecastPeriod: List<ForecastPeriod>) :
    RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    override fun getItemCount() = forecastPeriod.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.forecast_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(forecastPeriod[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateForecast(newForecast: List<ForecastPeriod>) {
        forecastPeriod = newForecast
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val monthTV: TextView = view.findViewById(R.id.tv_month)
        private val dayTV: TextView = view.findViewById(R.id.tv_day)
        private val highTempTV: TextView = view.findViewById(R.id.tv_high_temp)
        private val lowTempTV: TextView = view.findViewById(R.id.tv_low_temp)
        private val popTV: TextView = view.findViewById(R.id.tv_pop)
        private val shortDescTV: TextView = view.findViewById(R.id.tv_short_description)

        fun bind(forecastItem: ForecastPeriod) {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val date = dateFormat.parse(forecastItem.dateTime)

            val monthFormat = SimpleDateFormat("MMM", Locale.getDefault())
            val dayFormat = SimpleDateFormat("d", Locale.getDefault())

            monthTV.text = date?.let { monthFormat.format(it) }
            dayTV.text = date?.let { dayFormat.format(it) }

            highTempTV.text = String.format(Locale.getDefault(), "%.0f°F", forecastItem.main.tempMax)
            lowTempTV.text = String.format(Locale.getDefault(), "%.0f°F", forecastItem.main.tempMin)
            popTV.text = String.format(Locale.getDefault(), "%.0f%% precip.", forecastItem.probabilityOfPrecipitation * 100)
            shortDescTV.text = forecastItem.weather.firstOrNull()?.description ?: "No description"
        }
    }

}
