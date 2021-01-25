package com.wizeline.interviews.weather.locked

interface WeatherClient {
    fun getCities(): List<City>
}
