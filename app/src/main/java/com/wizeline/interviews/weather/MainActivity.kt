package com.wizeline.interviews.weather

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.wizeline.interviews.weather.adapter.CitiesAdapter
import com.wizeline.interviews.weather.locked.City
import com.wizeline.interviews.weather.locked.CoroutineWeatherClient
import com.wizeline.interviews.weather.locked.FakeWeatherClient
import com.wizeline.interviews.weather.locked.WeatherClient
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    lateinit var weatherClient: WeatherClient
    lateinit var coroutineWeatherClient: CoroutineWeatherClient

    private val citiesAdapter = CitiesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        setupRecyclerView()

        weatherClient = FakeWeatherClient()
        coroutineWeatherClient = CoroutineWeatherClient(weatherClient)
        loadCities()
    }

    private fun loadCities() {
        try {
            Log.d("MainActivity", "${weatherClient.getCities()}")
            val cityList = weatherClient.getCities().sortedBy { it.countryName }
            showCitiesList(cityList)
        } catch (e: Exception) {
            Log.e("MainActivity", "Error on get cities", e)
        }
    }

    private fun setupRecyclerView() {
        cities_recycler_view.adapter = citiesAdapter
        cities_recycler_view.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun showCitiesList(cityList: List<City>) {
        citiesAdapter.setCountryCityData(cityList)
    }
}
