package edu.oregonstate.cs492.assignment1
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar


class WeatherAdapter: RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {
    private val weatherInfo: MutableList<WeatherData> = mutableListOf<WeatherData>().apply {
        add(WeatherData("Feb 3", "69°F", "24°F", "0% PRECIP", "Sunny", "SUPER DUPER HOT ;)"))
        add(WeatherData("Feb 4", "49°F", "22°F", "70% PRECIP", "Afternoon / PM Showers", "PRETTY MEH NIGHT TBH"))
        add(WeatherData("Feb 5", "66°F", "26°F", "100% PRECIP", "All Day Rain", "DEPRESSION AT ITS FINEST"))
        add(WeatherData("Feb 6", "39°F", "34°F", "50% PRECIP", "AM Rain", "PERFECT COFFEE WEATHER"))
        add(WeatherData("Feb 8", "65°F", "45°F", "0% PRECIP", "Sunny","NICE COLD BUT SUNNY DAY"))
        add(WeatherData("Feb 9", "65°F", "55°F", "0% PRECIP", "Partly Cloudy", "ALL DOOM N GLOOM"))
        add(WeatherData("Feb 10", "69°F", "59°F", "0% PRECIP", "Sunny", "PERFECT DAY TO GO OUTSIDE"))
        add(WeatherData("Feb 11", "49°F", "31°F", "70% PRECIP", "Afternoon / PM Showers", "LATE NIGHT SHOWERS"))
        add(WeatherData("Feb 12", "66°F", "59°F", "100% PRECIP", "All Day Rain", "SAD INDOOR WEATHER DAY"))
        add(WeatherData("Feb 13", "39°F", "31°F", "50% PRECIP", "AM Rain", "DON'T FORGET YOUR UMBRELLA"))
        add(WeatherData("Feb 14", "65°F", "54°F", "0% PRECIP", "Sunny", "NO RAIN AND ONLY SUN"))
        add(WeatherData("Feb 15", "60°F", "49°F", "0% PRECIP", "Partly Cloudy", "PERFECT DAY FOR A RUN"))
    }
    class WeatherViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private var description: TextView = view.findViewById(R.id.tv_description_text)
        private var probability: TextView = view.findViewById(R.id.tv_probability_text)
        private var date: TextView = view.findViewById(R.id.tv_date_text)
        private var tempLow: TextView = view.findViewById(R.id.tv_tempLow_text)
        private var tempHigh: TextView = view.findViewById(R.id.tv_tempHigh_text)

        private fun displaySnackBar(view: View, text: String) {
            Snackbar.make(view, text, Snackbar.LENGTH_LONG).show()
        }
        fun bind(weatherData: WeatherData){
            description.text = weatherData.description
            probability.text = weatherData.probability
            date.text = weatherData.date
            tempLow.text = weatherData.tempLow
            tempHigh.text = weatherData.tempHigh


            itemView.setOnClickListener{view ->
                displaySnackBar(view, weatherData.snackDescription)
                true
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.weather_list_item, parent, false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(weatherInfo[position])
    }

    override fun getItemCount() = weatherInfo.size


}