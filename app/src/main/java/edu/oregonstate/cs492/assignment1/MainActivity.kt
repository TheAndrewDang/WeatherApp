package edu.oregonstate.cs492.assignment1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val coordinatorLayout = findViewById<CoordinatorLayout>(R.id.coordinator_layout)
        val weatherListRV: RecyclerView = findViewById(R.id.rv_weather_list)

        weatherListRV.layoutManager = LinearLayoutManager(this)
        weatherListRV.setHasFixedSize(true)

        val adapter = WeatherAdapter()
        weatherListRV.adapter = adapter

    }
}