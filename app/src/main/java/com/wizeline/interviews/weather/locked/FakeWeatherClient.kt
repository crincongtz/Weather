package com.wizeline.interviews.weather.locked

import kotlinx.coroutines.delay
import kotlin.random.Random

private val countriesWithCities: Map<String, List<String>> = mapOf(
    "Mexico" to listOf("Guadalajara", "Mexico City", "Monterrey", "Queretaro"),
    "Colombia" to listOf("Bogota", "Cali", "Cartagena"),
    "United States" to listOf("Seattle", "San Francisco", "New York", "Los Angeles"),
    "Nigeria" to listOf("Lagos", "Kano"),
    "Spain" to listOf("Barcelona", "Madrid"),
    "Australia" to listOf("Canberra", "Melbourne", "Perth", "Sydney"),
    "India" to listOf("Mumbai", "Chennai", "Kolkata"),
    "Egypt" to listOf("Cairo", "Alexandria"),
    "Vietnam" to listOf("Ho Chi Minh", "Hanoi")
)

class FakeWeatherClient : WeatherClient {
    override fun getCities(): List<City> {
        randomDelay()
        return fakeCities()
    }

    private fun randomDelay() {
        try {
            Thread.sleep(Random.nextLong(5000))
        } catch (e: InterruptedException) {
            Thread.currentThread().interrupt()
        }
    }

    private fun fakeCities(): List<City> {
        val listSize = Random.nextInt(10, 30)
        return (0 until listSize).map {
            val country = countriesWithCities.keys.random()
            val city = countriesWithCities.getValue(country).random()
            City(
                name = city,
                countryName = country,
                latitude = (90 * Random.nextFloat()).toDouble(),
                longitude = (180 * Random.nextFloat()).toDouble()
            )
        }
    }
}

class CoroutineWeatherClient(
    private val weatherClient: WeatherClient
) {
    suspend fun getCities(): List<City> {
        delay(Random.nextLong(2000, 5000))
        return weatherClient.getCities()
    }
}
